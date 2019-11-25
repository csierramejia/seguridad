FROM openjdk:8-jre-alpine
ADD target/seguridad-0.0.1-SNAPSHOT.jar seguridad.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/seguridad.jar"]
EXPOSE 18080