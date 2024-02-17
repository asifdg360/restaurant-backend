# Use a base image with OpenJDK for Java 17
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/rms-backend-0.0.1-SNAPSHOT.jar /app/rms-backend.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "rms-backend.jar"]