FROM maven:3-jdk-11-slim AS builder
COPY pom.xml /app/pom.xml
COPY src /app/src
WORKDIR /app
RUN mvn clean package -DskipTests=true

FROM openjdk:11-jdk-slim
COPY --from=builder /app/target/*jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
