FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY /target/*.jar /app/app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "./app.jar"]