# Use an official Maven image as a parent image
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven Wrapper and POM file
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Copy the rest of the application source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image for the final image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file from the build stage to the current working directory in the container
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 to the outside world
EXPOSE 8180

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
