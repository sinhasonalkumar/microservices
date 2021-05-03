FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR /application
ARG JAR_FILE=target/order-orchestrator-service.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM gcr.io/distroless/java:11
                
EXPOSE 8080

WORKDIR /application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
        
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]