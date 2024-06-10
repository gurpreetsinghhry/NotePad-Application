# Use an official OpenJDK runtime as the base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file and any other necessary files into the container
COPY target/NotePadApplication-0.0.1-SNAPSHOT.jar /app/NotePadApplication.jar

# Expose the port that the application will run on
EXPOSE 8080

# Define the command to run your application
ENTRYPOINT  ["java", "-jar", "NotePadApplication.jar"]
