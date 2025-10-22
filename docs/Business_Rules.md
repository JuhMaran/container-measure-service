# Business Rules

Essas regras tornam o microsserviço funcional e consistente com um contexto de gestão de recipientes (container
measures):

### 1. Cadastro de medidas de recipientes

* Cada combinação `category + type + volumeMl` deve ser única.
* Evita duplicidade no cadastro.

### 2. Volume mínimo e máximo permitido

* Exemplo: não cadastrar recipientes menores que 30ml nem maiores que 50.000ml (50L).

### 3. Status ativo/inativo

* Permitir inativar uma medida sem excluir do banco (soft delete).
* Consultas padrão devem retornar apenas ativos.

### 4. Auditoria de criação e atualização

* `createdDate` e `updateDate` sempre preenchidos automaticamente.

### 5. Validação de compatibilidade categoria/tipo

* Exemplo:
    * `ContainerCategory.GLASS` aceita tipos `PINT`, `TASTER`, `WEIZEN`, etc.
    * `ContainerCategory.BOTTLE` aceita `STUBBY`, `BOMBER`, etc.
* Se o tipo não for compatível com a categoria, rejeitar a requisição (validação de negócio).

### 6. Não permitir remoção física

* Apenas inativar (`active = false`), mantendo histórico.

### 7. Ordenação padrão nas listagens

* Ordenar por `category`, `type` e `volumeMl` (ascendente).

### 8. Pesquisa flexível

* Permitir filtros por:
    * Categoria (category)
    * Tipo (type)
    * Status (ativo/inativo)
    * Volume exato ou intervalo (mínimo/máximo)

---

## Funcionalidades Principais Esperadas

Essas são as principais features que o microsserviço deve oferecer via API REST:

| Funcionalidade       | Descrição                                             | Método                                             |
|:---------------------|:------------------------------------------------------|:---------------------------------------------------|
| **Criar medida**     | Cadastra uma nova medida de recipiente                | `POST /api/v1/container-measures`                  |
| **Listar medidas**   | Lista todas as medidas (paginação, filtro por status) | `GET /api/v1/container-measures`                   |
| **Buscar por ID**    | Retorna uma medida específica                         | `GET /api/v1/container-measures/{id}`              |
| **Filtrar**          | Buscar por categoria, tipo, ativo/inativo             | `GET /api/v1/container-measures/search`            |
| **Atualizar medida** | Atualiza atributos válidos (sem recriar)              | `PUT /api/v1/container-measures/{id}`              |
| **Inativar medida**  | Marca a medida como inativa (soft delete)             | `PATCH /api/v1/container-measures/{id}/deactivate` |
| **Reativar medida**  | Reativa uma medida inativa                            | `PATCH /api/v1/container-measures/{id}/activate`   |

---

## Próximos passos recomendados

1. Adicionar camada **Service** com validações de negócio (ex: categoria/tipo, duplicidade).
2. Criar **Controller REST** com `@Validated` e tratamento global de exceções (`@ControllerAdvice`).
3. Implementar mensagens de erro padrão em português (via `messages.properties`).
4. Escrever testes unitários e de integração (`@DataJpaTest`, `@SpringBootTest`).
5. **Tratamento Global de Exceções** (`ExceptionHandler`)
6. Adicionar a validação de compatibilidade entre **categoria** e **tipo**.
7. Service Layer
    * Capturar exceção de violação de constraint e lançar mensagem amigável para o usuário (
      `"Já existe uma medida cadastrada com esta categoria, tipo e volume."`).
8. Testes de Integração
    * Inserir registros duplicados propositalmente para validar que a constraint funciona.
9. Combinar com índices
    * O índice composto `idx_container_category_type_active` continua válido e otimiza consultas filtradas.
10. DTO com validação e mensagens em português.
11. Scripts Flyway V001, V002 e V003 aplicáveis para H2 e MySQL
