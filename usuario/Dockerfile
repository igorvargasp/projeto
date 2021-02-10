FROM maven:3.6.1-jdk-11

ARG ARG_DOCKER_TAG=API_USUARIO

EXPOSE 8082

COPY . /usuario
WORKDIR /usuario

RUN mvn clean install package -DskipTests

ENTRYPOINT ["java", "-jar", "target/usuario-1.0.0-SNAPSHOT.jar"]