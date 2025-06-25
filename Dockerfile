# Etapa de build
FROM ubuntu:latest AS build
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk maven
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:21-jre
COPY --from=build /app/target/FitGymGpt-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]