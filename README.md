# Container Measure Service

Microsserviço responsável por gerenciar tamanhos padronizados de copos, garrafas e outros recipientes utilizados em
choperias e estabelecimentos de bebidas.

---

## Tecnologias

* Java 25
* Spring Boot 3.5.6
* Maven 3.9.11

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

## Exemplos de Request e Responses

### GET

**Request**

```
curl --location 'http://localhost:9093/api/v1/container-measures'
```

**Response**

```json
[
  {
    "id": 1,
    "category": "GLASS",
    "type": "TASTER",
    "volumeMl": 100,
    "description": "Copo degustação pequeno",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 2,
    "category": "GLASS",
    "type": "HALF",
    "volumeMl": 250,
    "description": "Copo pequeno de 250 ml",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 3,
    "category": "GLASS",
    "type": "PINT",
    "volumeMl": 473,
    "description": "Copo americano padrão 16 oz",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 4,
    "category": "GLASS",
    "type": "IMPERIAL_PINT",
    "volumeMl": 568,
    "description": "Pint imperial britânico (20 oz)",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 5,
    "category": "GLASS",
    "type": "TULIP",
    "volumeMl": 330,
    "description": "Copo tipo tulipa para cervejas belgas",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 6,
    "category": "GLASS",
    "type": "SNIFTER",
    "volumeMl": 300,
    "description": "Copo globo para cervejas encorpadas",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 7,
    "category": "GLASS",
    "type": "WEIZEN",
    "volumeMl": 500,
    "description": "Copo alto para cervejas de trigo",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 8,
    "category": "GLASS",
    "type": "STEIN",
    "volumeMl": 1000,
    "description": "Caneca tradicional alemã de 1 litro",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 9,
    "category": "BOTTLE",
    "type": "STUBBY",
    "volumeMl": 330,
    "description": "Garrafa padrão 330 ml",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 10,
    "category": "BOTTLE",
    "type": "BOMBER",
    "volumeMl": 650,
    "description": "Garrafa 650 ml",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 11,
    "category": "BOTTLE",
    "type": "CROWLER",
    "volumeMl": 950,
    "description": "Lata grande de 950 ml (crowler)",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 12,
    "category": "BOTTLE",
    "type": "HOWLER",
    "volumeMl": 950,
    "description": "Garrafa intermediária de 950 ml",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 13,
    "category": "BOTTLE",
    "type": "GROWLER",
    "volumeMl": 1890,
    "description": "Garrafa refill de 1.89 litros",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 14,
    "category": "KEG",
    "type": "MINI_KEG",
    "volumeMl": 5000,
    "description": "Mini barril doméstico 5L",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 15,
    "category": "KEG",
    "type": "GROWLER",
    "volumeMl": 2000,
    "description": "Growler pressurizado 2L",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  },
  {
    "id": 16,
    "category": "KEG",
    "type": "CROWLER",
    "volumeMl": 950,
    "description": "Lata pressurizada 950 ml para chope artesanal",
    "active": true,
    "createdDate": "2025-10-18T15:25:12.887766",
    "updateDate": "2025-10-18T15:25:12.887766"
  }
]
```

