FROM maven:3.6.1-jdk-11

ARG ARG_DOCKER_TAG=API_PRODUTO

EXPOSE 8081

COPY . /produto
WORKDIR /produto

RUN mvn clean install package -DskipTests

ENTRYPOINT ["java", "-jar", "target/produto-1.0.0-SNAPSHOT.jar"]