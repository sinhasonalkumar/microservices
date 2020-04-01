FROM openjdk:12-jdk-alpine
ARG JAR_FILE=target/kubernetes-springboot-demo-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]