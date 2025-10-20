# ==========================================
# CONTAINER MEASURE SERVICE - Dockerfile
# ==========================================
FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app
COPY . .

RUN apk add --no-cache maven && mvn clean package -DskipTests

ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENV APP_PORT=8093

EXPOSE ${APP_PORT}
CMD ["sh", "-c", "java $JAVA_OPTS -jar target/*.jar"]
