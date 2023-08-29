# Stage 1: Build the Spring Boot application
FROM amazoncorretto:17 as build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Create a runtime container
FROM amazoncorretto:17 as runtime
WORKDIR /app
COPY --from=build /app/target/*.jar /app/spring-boot-app.jar
CMD ["java", "-jar", "spring-boot-app.jar"]