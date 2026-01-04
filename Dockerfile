# Multi-stage Dockerfile for building and running the Spring Boot application

FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only the files needed to build first to leverage Docker layer cache
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Build the application (skip tests for faster builds; remove -DskipTests to run tests)
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
