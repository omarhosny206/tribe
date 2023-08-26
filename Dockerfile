FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY ./target/*.jar spring-app.jar

CMD [ "java", "-jar", "spring-app.jar" ]