FROM amazoncorretto:17-alpine-jdk

COPY build/libs/*.jar  UsersService.jar

ENTRYPOINT ["java", "-jar", "UsersService.jar"]