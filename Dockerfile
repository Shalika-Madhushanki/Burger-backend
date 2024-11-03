## Use an official OpenJDK runtime as a parent image
#FROM openjdk:17-jdk-slim
#
## Set the working directory
#WORKDIR /app
#
## Copy the JAR file built by Gradle/Maven
#COPY build/libs/ahoi-burger-0.0.1-SNAPSHOT.jar app.jar
#
## Expose the application port
#EXPOSE 8080
#
## Run the JAR file
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Use a Gradle image to build the project
FROM gradle:7.6.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Use OpenJDK to run the application
FROM openjdk:17-jdk-slim
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
