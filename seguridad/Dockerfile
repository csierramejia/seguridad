FROM openjdk:8-jre-alpine
ADD target/escrutinio-0.0.1-SNAPSHOT.jar escrutinio.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/escrutinio.jar"]
EXPOSE 8080