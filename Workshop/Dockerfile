FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*SNAPSHOT.jar app.jar

ENV APP_COMMIT=unknown
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--app.commit=${APP_COMMIT}"]
