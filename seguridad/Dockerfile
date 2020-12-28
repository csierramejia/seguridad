FROM amazoncorretto:8
ADD target/seguridad-0.0.1-SNAPSHOT.jar seguridad.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/seguridad.jar"]
EXPOSE 8089