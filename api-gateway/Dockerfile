FROM amazoncorretto:17-alpine-jdk

COPY build/libs/*.jar  ApiGatewayService.jar

ENTRYPOINT ["java", "-jar", "ApiGatewayService.jar"]