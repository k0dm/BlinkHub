FROM amazoncorretto:17-alpine-jdk

COPY build/libs/*.jar DiscoveryService.jar

ENTRYPOINT ["java", "-jar", "DiscoveryService.jar"]