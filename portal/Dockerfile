FROM maven:3.8.3-jdk-11 as builder
RUN mkdir /web
WORKDIR /web
COPY . .
RUN mvn -DskipTests=true clean package

FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /web
COPY --from=builder /web/target/*.jar /web/app.jar
ENTRYPOINT ["java","-jar","/web/app.jar"]