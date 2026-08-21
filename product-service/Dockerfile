FROM eclipse-temurin:25-jre

WORKDIR /app

COPY target/*.jar app.jar

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar /otel-javaagent.jar

EXPOSE 8080

ENTRYPOINT ["java","-javaagent:/otel-javaagent.jar","-jar","app.jar"]
