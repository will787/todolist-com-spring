FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY . .

RUN mvn clean install

FROM adoptopenjdk:17-jre-hotspot-bionic

WORKDIR /app

COPY --from=build /target/todolist-1.0.0.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
