FROM maven:3.6.1-jdk-11

ARG ARG_DOCKER_TAG=API_PEDIDO

EXPOSE 8080

COPY . /api-pedido
WORKDIR /api-pedido

RUN mvn clean install package -DskipTests

ENTRYPOINT ["java", "-jar", "target/pedido-1.0.0-SNAPSHOT.jar"]