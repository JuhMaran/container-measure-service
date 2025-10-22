# Spring Doc - OpenAPI

| Tipo                                 | Anotações usadas                                                                                        |
|--------------------------------------|---------------------------------------------------------------------------------------------------------|
| **Documento**                        | `@OpenAPIDefinition`, `@Info`, `@Contact`, `@License`, `@Server`, `@Tag`                                |
| **Segurança**                        | `@SecurityScheme`, `@SecurityRequirement`                                                               |
| **Controller/Operação**              | `@Operation`, `@ApiResponse`, `@RequestBody`, `@Parameter`                                              |
| **Schema/Model**                     | `@Schema`, `@ArraySchema`, `@Content`                                                                   |
| **Enumeração & propriedades extras** | uso de `@Schema` com `allowableValues`, `requiredMode`, `defaultValue`, `example`, `minimum`, `maximum` |

| Elemento            | Anotações usadas                                                                             |
|---------------------|----------------------------------------------------------------------------------------------|
| Classe DTO          | `@Schema(name, description)`                                                                 |
| Campos              | `@Schema(description, example, requiredMode, enumAsRef, maxLength, minimum, implementation)` |
| Enum referência     | `enumAsRef = true`                                                                           |
| Campos compostos    | `implementation = VolumeRangeDTO.class`, `implementation = PaginationDTO.class`              |
| Validação integrada | Integração automática via Bean Validation (`@NotNull`, `@Positive`, `@Size`, `@Min`)         |

| Tipo                          | Descrição                                                                               |
|-------------------------------|-----------------------------------------------------------------------------------------|
| `@Tag`                        | Agrupa endpoints no Swagger UI em seções claras (“Container Measures”, “Health Check”). |
| `@ExternalDocumentation`      | Adiciona link direto para a documentação ou repositório no Swagger.                     |
| `@SecurityScheme` (comentado) | Deixa preparado caso o microsserviço utilize autenticação via JWT futuramente.          |
| `@Server`                     | Descrições mais legíveis e diferenciação de ambientes.                                  |
| `@Info` enriquecido           | Agora contém termos de serviço e descrição expandida.                                   |
