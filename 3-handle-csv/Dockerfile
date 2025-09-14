FROM gradle:9.0.0-jdk-21-and-24-noble AS gradle-and-jdk

WORKDIR /app

COPY . .

RUN gradle build --no-daemon

FROM openjdk:21-slim

WORKDIR /app

COPY --from=gradle-and-jdk /app/build/libs/*.jar ./app.jar

CMD ["java", "-Xmx64m", "-jar", "app.jar"]