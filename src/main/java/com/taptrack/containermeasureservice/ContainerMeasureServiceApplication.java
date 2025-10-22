package com.taptrack.containermeasureservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Classe principal da aplicação Container Measure Service.
 * Responsável por inicializar o microsserviço e definir as configurações globais do OpenAPI/Swagger.
 */
@OpenAPIDefinition(
  info = @Info(
    title = "Container Measure Service",
    version = "1.0.1",
    description = "Microsserviço responsável por gerenciar tamanhos padronizados de copos, garrafas e outros recipientes "
      + "utilizados em choperias e estabelecimentos de bebidas. "
      + "Permite o cadastro, listagem, atualização e filtragem de medidas com base em volume, categoria e tipo.",
    termsOfService = "https://taptrack.com/terms",
    contact = @Contact(
      name = "Juliane Maran",
      url = "https://github.com/JuhMaran",
      email = "julianemaran@gmail.com"
    ),
    license = @License(
      name = "Apache License 2.0",
      url = "https://www.apache.org/licenses/LICENSE-2.0.html"
    )
  ),
  servers = {
    @Server(
      url = "http://localhost:8093",
      description = "Ambiente Local - Desenvolvimento"
    ),
    @Server(
      url = "https://api.taptrack.com",
      description = "Ambiente de Produção"
    )
  },
  tags = {
    @Tag(
      name = "Container Measures",
      description = "Operações relacionadas ao gerenciamento de medidas de recipientes (CRUD e filtros)."
    ),
    @Tag(
      name = "Health Check",
      description = "Verificação de disponibilidade e status do microsserviço."
    )
  },
  externalDocs = @ExternalDocumentation(
    description = "Documentação técnica do projeto no GitHub",
    url = "https://github.com/JuhMaran/container-measure-service"
  )
)
// Caso venha a usar autenticação JWT futuramente, basta descomentar o bloco abaixo:
// @SecurityScheme(
//     name = "bearerAuth",
//     scheme = "bearer",
//     type = SecuritySchemeType.HTTP,
//     bearerFormat = "JWT"
// )
@EnableDiscoveryClient
@SpringBootApplication
public class ContainerMeasureServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContainerMeasureServiceApplication.class, args);
  }

}
