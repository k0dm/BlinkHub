FROM amazoncorretto:17-alpine-jdk

COPY build/libs/*.jar  CatalogService.jar

ENTRYPOINT ["java", "-jar", "CatalogService.jar"]