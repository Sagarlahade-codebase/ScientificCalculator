# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first for Docker layer caching
COPY pom.xml .

# Copy source code
COPY src ./src

# Build with Maven, skip tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Expose Render's dynamic port
EXPOSE $PORT

# Copy the exact JAR from build stage
COPY --from=build /app/target/CalculatorApplication-0.0.1-SNAPSHOT.jar app.jar

# JVM optimizations + entrypoint
ENTRYPOINT ["java", "-XX:InitialRAMPercentage=75.0", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
