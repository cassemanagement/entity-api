FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/entity-api.jar
WORKDIR /opt/app
COPY ${JAR_FILE} /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
