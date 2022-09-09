FROM openjdk:11-jdk-slim as builder
COPY . /src
WORKDIR /src
RUN ./mvnw clean package

FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=builder /src/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar" ]