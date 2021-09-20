FROM gcr.io/distroless/java:11
COPY target/order-service-otel.jar application.jar
COPY agent/opentelemetry-javaagent-all.jar /etc/agent/opentelemetry-javaagent-all.jar
EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:/etc/agent/opentelemetry-javaagent-all.jar" , "-jar", "application.jar"]