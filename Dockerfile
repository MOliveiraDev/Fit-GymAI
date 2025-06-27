FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM registry.access.redhat.com/ubi9/openjdk-21
COPY --from=build /app/target/FitGymGpt-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]