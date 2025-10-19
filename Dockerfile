# --------------------------------------
# Etapa 1: Build (Compilação)
# --------------------------------------
# A imagem base 'maven' já inclui um JDK, o que a torna ideal para a compilação.
FROM maven:3.9.11-eclipse-temurin-25 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml para que o Docker possa usar o cache do Maven
COPY pom.xml .

# Baixa as dependências
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Executa o build da aplicação, ignorando os testes
RUN mvn clean package -DskipTests

# --------------------------------------
# Etapa 2: Runtime (Tempo de Execução)
# --------------------------------------
# Usa uma imagem JRE menor e otimizada para a execução final.
# A versão JRE deve corresponder à versão JDK usada no estágio de build
FROM eclipse-temurin:25-jdk-alpine

# Cria um diretório de trabalho para a aplicação
WORKDIR /app

# Copia o arquivo JAR compilado do estágio de build para a imagem final
COPY --from=build /app/target/*.jar /app/app.jar

ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENV APP_PORT=9093

# Expõe a porta que a aplicação Java utiliza
EXPOSE ${APP_PORT}

# Comando para iniciar a aplicação uqando o contêiner for executado.
#CMD [ "java", "-jar", "/app/app.jar" ]

ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -jar app.jar" ]
