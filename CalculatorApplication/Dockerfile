# Stage 1: Build the application
FROM ubuntu:latest AS build

# Install dependencies
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Set the working directory inside the container
WORKDIR /app

# Copy the application source code to the working directory
COPY . .

# Run Maven to build the application, skipping tests
RUN mvn clean install -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Expose the application port
EXPOSE 8080

# Copy the built JAR file from the build stage
COPY --from=build /app/target/spring-boot-CalculatorApplication-0.0.1-SNAPSHOT.jar app.jar


# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
