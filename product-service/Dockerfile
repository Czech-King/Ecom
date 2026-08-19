FROM eclipse-temurin:25-jre

WORKDIR /app

COPY target/*.jar app.jar
COPY opentelemetry-javaagent.jar opentelemetry-javaagent.jar

EXPOSE 8080

ENTRYPOINT ["java","-javaagent:/app/opentelemetry-javaagent.jar","-jar","app.jar"]
