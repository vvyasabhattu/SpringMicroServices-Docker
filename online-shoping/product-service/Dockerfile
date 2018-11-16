FROM openjdk:8-jdk-alpine

WORKDIR SpringBootDocker/Git/SpringMicroServices-Docker/online-shoping/product-service

ADD target/product-service-0.0.1-SNAPSHOT.jar product-service-0.0.1-SNAPSHOT.jar

CMD exec java -jar product-service-0.0.1-SNAPSHOT.jar

EXPOSE 9099:9099
