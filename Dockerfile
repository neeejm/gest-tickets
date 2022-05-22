FROM openjdk:17-alpine

WORKDIR /app

COPY ./target/devoir-1.jar app.jar

CMD [ "java", "-jar", "app.jar", "--server.port=${PORT:8080}" ]