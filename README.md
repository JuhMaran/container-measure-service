# Container Measure Service

Microsserviço responsável por gerenciar tamanhos padronizados de copos, garrafas e outros recipientes utilizados em
choperias e estabelecimentos de bebidas.

---

## Acessar

* Acessar Swagger UI: http://localhost:8093/api/v1/swagger-ui/index.html
* Acessar JSON da OpenAPI: http://localhost:8093/api/v1/v3/api-docs
* Acessar YAML da OpenAPI: http://localhost:8093/v3/api-docs.yaml

---

## Executar a Aplicação

### Usando Docker

1. Acessar o `wsl`
2. Build Manual: `docker build -t container-measure-service .`
3. Rodar container: `docker run -p 8093:8093 container-measure-service`
4. Logs em tempo real: `docker logs -f <container-id>`

### Rede Docker

Se quiser que os serviços conversem entre si localmente

```bash
docker network create taptrack-net
docker run -d --network taptrack-net --name eureka eureka-server
docker run -d --network taptrack-net --name gateway api-gateway
docker run -d --network taptrack-net --name container-measure container-measure-service
docker run -d --network taptrack-net --name frontend -p 4200:4200 taptrack-frontend
```

---

## Tecnologias

* Java 25
* Spring Boot 3.5.6
* Maven 3.9.11
  [README.md](README.md)
  URL H2 Database: http://localhost:9093/api/v1/h2-console

---

* **Saved Settings:** Generic H2 (Embedded)
* **Setting Name:** Generic H2 (Embedded)
* **Driver Class:** org.h2.Driver
* **JDBC URL:** jdbc:h2:mem:container_measure_db
* **User Name:** sa
* **Password:** <Deixar em Branco>
* "Connect" ou "Test Connection"

```sql
SELECT * FROM CONTAINER_MEASURE
SELECT * FROM "flyway_schema_history"
```

**Tabela:** CONTAINER_MEASURE

| ID  | VERSION | CATEGORY | TYPE     | VOLUME_ML | DESCRIPTION               | ACTIVE | CREATED_DATE                 | UPDATE_DATE                  |
|:----|:--------|:---------|:---------|:----------|:--------------------------|:-------|:-----------------------------|:-----------------------------|
| `1` | `0`     | `GLASS`  | `TASTER` | `100`     | `Copo degustação pequeno` | `TRUE` | `2025-10-18 20:58:09.389932` | `2025-10-18 20:58:09.389932` |

---

## Tamanhos Comuns em Choperias

### Copos (Glass Sizes)

| Nome comercial     | Tipo (Enum sugerido) | Volume (mL) | Descrição                                                            |
|--------------------|----------------------|-------------|----------------------------------------------------------------------|
| **Taster**         | `TASTER`             | 150         | Copo degustação — usado em flights ou amostras                       |
| **Half Pint**      | `HALF`               | 250         | Copo pequeno (metade de uma pint)                                    |
| **Pint**           | `PINT`               | 473         | Padrão americano — 16 oz                                             |
| **Imperial Pint**  | `IMPERIAL_PINT`      | 568         | Padrão inglês                                                        |
| **Tulip**          | `TULIP`              | 330         | Copo arredondado, usado para IPAs e Belgian ales                     |
| **Snifter**        | `SNIFTER`            | 300         | Copo arredondado para cervejas fortes e aromáticas (ex: Barley Wine) |
| **Weizen**         | `WEIZEN`             | 500         | Copo alto para cervejas de trigo                                     |
| **Stein**          | `STEIN`              | 1000        | Caneca grande tradicional alemã (1 litro)                            |
| **Growler (Copo)** | `MINI_GROWLER`       | 1000        | Versão menor de growler para consumo imediato                        |

### Garrafas e Growlers (Bottle Sizes)

| Nome comercial | Tipo (Enum sugerido) | Volume (mL) | Descrição                                   |
|----------------|----------------------|-------------|---------------------------------------------|
| **Stubby**     | `STUBBY`             | 330         | Garrafa pequena tradicional (ex: long neck) |
| **Bomber**     | `BOMBER`             | 650         | Garrafa artesanal intermediária             |
| **Crowler**    | `CROWLER`            | 946         | Lata de chope envasada sob pressão (32 oz)  |
| **Growler**    | `GROWLER`            | 1890        | Recipiente retornável para chope            |
| **Howler**     | `HOWLER`             | 950         | Metade de um growler                        |
| **Mini Keg**   | `MINI_KEG`           | 5000        | Barril pequeno (5 L) para consumo doméstico |

---

## Estrutura da Aplicação

```
container-measure-service
 ├── src/
 ├── .gitignore
 ├── Dockerfile
 ├── pom.xml
 └── README.md

--- 

src/
 ├── main/
 │    ├── java/
 │    │    └── com.taptrack.containermeasureservice/
 │    │        └── ContainerMeasureServiceApplication.java
 │    └── resources/
 │         └── db/
 │         │   └── migration/
 │         │       ├── h2/                  # Desenvolvimento
 │         │       │   └── V1__create_and_insert_container_measure.sql
 │         │       └── mysql/               # Produção
 │         │           └── V1__create_and_insert_container_measure.sql
 │         ├── application.yml              # Configuração base
 │         ├── application-dev.yml          # H2 - Desenvolvimento e Testes
 │         ├── application-prod.yml         # MySQL - Produção
 │         └── logback-spring.xml           # Deve criar um logback-spring.xml com logs coloridos
 └── test/
```

### Estrutura de Pacotes (DDD + Clean Architecture)

```
com.taptrack.containermeasureservice
├── application                                         # Application Layer
│   ├── controller
│   │   └── ContainerMeasureController.java
│   ├── dto
│   │   ├── request
│   │   │   └── ContainerMeasureRequestDTO.java
│   │   └── response
│   │       └── ContainerMeasureResponseDTO.java
│   └── mapper
│       └── ContainerMeasureMapper.java
│
├── domain                                              # Domain Layer
│   ├── model
│   │   ├── ContainerMeasure.java
│   │   └── enums
│   │       ├── ContainerCategory.java
│   │       └── ContainerType.java
│   └── repository
│       └── ContainerMeasureRepository.java
│
├── infrastructure                                      # Infrastructure Layer
│   ├── service
│   │   ├── ContainerMeasureService.java
│   │   └── impl
│   │       └── ContainerMeasureServiceImpl.java
│   └── config
│       └── OpenApiConfig.java
│
└── ContainerMeasureServiceApplication.java
```

---

## Perfis

| Perfil   | Banco                       | Flyway Location                | Observação                        |
|----------|-----------------------------|--------------------------------|-----------------------------------|
| **dev**  | H2 (em memória)             | `classpath:db/migration/h2`    | usado para desenvolvimento rápido |
| **prod** | MySQL                       | `classpath:db/migration/mysql` | ambiente real / deploy            |
| **test** | H2 (em memória, modo MySQL) | desabilitado                   | ideal para testes unitários       |

### Ativar Perfil

Para ativar o perfil desejado, adicione no seu `application-dev.yml`, `application-prod.yml` ou defina via argumento:

```bash
-Dspring.profiles.active=prod
```

ou no `application.yml` principal:

```yaml
spring:
  profiles:
    active: dev
```

---

## cURLs

### GET /list

**Request**

```
curl --location 'http://localhost:8080/api/v1/container-measures/list?category=GLASS&type=PINT&volume=300&active=true&minVolume=100&maxVolume=500&page=0&size=10' \
--header 'Accept: application/json'
```

**Response**

```json
{
  "content": [],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalPages": 0,
  "totalElements": 0,
  "size": 10,
  "number": 0,
  "first": true,
  "numberOfElements": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "empty": true
}
```

### GET /list - filtro por categoria

**Request**

```
curl --location 'http://localhost:8093/api/v1/container-measures/list?category=GLASS' \
--header 'Accept: application/json'
```

**Response**

```json
{
  "content": [
    {
      "id": 2,
      "category": "GLASS",
      "type": "HALF",
      "volumeMl": 250,
      "description": "Copo pequeno de 250 ml",
      "active": true,
      "createdDate": "2025-10-20T22:48:14.660834",
      "updateDate": "2025-10-20T22:48:14.660834"
    },
    {
      "id": 4,
      "category": "GLASS",
      "type": "IMPERIAL_PINT",
      "volumeMl": 568,
      "description": "Pint imperial britânico (20 oz)",
      "active": true,
      "createdDate": "2025-10-20T22:48:14.660834",
      "updateDate": "2025-10-20T22:48:14.660834"
    },
    {
      "id": 3,
      "category": "GLASS",
      "type": "PINT",
      "volumeMl": 473,
      "description": "Copo americano padrão 16 oz",
      "active": true,
      "createdDate": "2025-10-20T22:48:14.660834",
      "updateDate": "2025-10-20T22:48:14.660834"
    },
    {
      "id": 6,
      "category": "GLASS",
      "type": "SNIFTER",
      "volumeMl": 300,
      "description": "Copo globo para cervejas encorpadas",
      "active": true,
      "createdDate": "2025-10-20T22:48:14.660834",
      "updateDate": "2025-10-20T22:48:14.660834"
    },
    {
      "id": 8,
      "category": "GLASS",
      "type": "STEIN",
      "volumeMl": 1000,
      "description": "Caneca tradicional alemã de 1 litro",
      "active": true,
      "createdDate": "2025-10-20T22:48:14.660834",
      "updateDate": "2025-10-20T22:48:14.660834"
    },
    {
      "id": 1,
      "category": "GLASS",
      "type": "TASTER",
      "volumeMl": 100,
      "description": "Copo degustação pequeno",
      "active": true,
      "createdDate": "2025-10-20T22:48:14.660834",
      "updateDate": "2025-10-20T22:48:14.660834"
    },
    {
      "id": 5,
      "category": "GLASS",
      "type": "TULIP",
      "volumeMl": 330,
      "description": "Copo tipo tulipa para cervejas belgas",
      "active": true,
      "createdDate": "2025-10-20T22:48:14.660834",
      "updateDate": "2025-10-20T22:48:14.660834"
    },
    {
      "id": 7,
      "category": "GLASS",
      "type": "WEIZEN",
      "volumeMl": 500,
      "description": "Copo alto para cervejas de trigo",
      "active": true,
      "createdDate": "2025-10-20T22:48:14.660834",
      "updateDate": "2025-10-20T22:48:14.660834"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 8,
  "size": 10,
  "number": 0,
  "first": true,
  "numberOfElements": 8,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "empty": false
}
```