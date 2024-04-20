# Use an OpenJDK 17 image as base
FROM amazoncorretto:17-alpine-jdk

VOLUME /tmp

# Copy the compiled JAR file into the container
COPY build/libs/*.jar ConfigService.jar

ENTRYPOINT ["java", "-jar", "ConfigService.jar"]