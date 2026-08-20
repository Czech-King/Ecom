FROM eclipse-temurin:25-jre

WORKDIR /app

ARG OTEL_AGENT_VERSION=2.21.0

RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && curl -fsSL \
       -o /app/opentelemetry-javaagent.jar \
       https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_AGENT_VERSION}/opentelemetry-javaagent.jar \
    && apt-get remove -y curl \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-javaagent:/app/opentelemetry-javaagent.jar","-jar","app.jar"]
