FROM eclipse-temurin:17-jre-alpine   # wenn du sowieso auf neue Spring Boot Version gehst

EXPOSE 8080

WORKDIR /usr/app
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
