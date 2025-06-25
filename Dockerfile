FROM registry.access.redhat.com/ubi9/openjdk-21 AS build
RUN microdnf install -y maven && microdnf clean all
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM registry.access.redhat.com/ubi9/openjdk-21
COPY --from=build /app/target/FitGymGpt-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]