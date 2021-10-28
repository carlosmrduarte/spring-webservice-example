FROM maven:3.8.3-openjdk-17-slim@sha256:950889e13c6a50fa7426d67369d3afbc59608981883e22133cd385fc209c831b AS builder
WORKDIR /app
COPY pom.xml pom.xml
COPY src src
COPY .git .git
RUN mvn clean package -DskipTests --batch-mode

FROM openjdk:17-alpine@sha256:a996cdcc040704ec6badaf5fecf1e144c096e00231a29188596c784bcf858d05
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=builder /app/target/*jar /app/app.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "dumb-init" "java" "-jar" "app.jar"
