#FROM adoptopenjdk/openjdk11
#FROM openjdk:11-jre-slim
#EXPOSE 8080
#WORKDIR application
#ARG JAR_FILE=target/profile-service.jar
#ADD ${JAR_FILE} application.jar
#ENTRYPOINT ["java","-jar","/application.jar"]


FROM openjdk:11-jre-slim as builder
WORKDIR application
ARG JAR_FILE=target/profile-service.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:11-jre-slim
EXPOSE 8080
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]