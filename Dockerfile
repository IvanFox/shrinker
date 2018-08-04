FROM openjdk:8-jdk
MAINTAINER <zvezdo4eet@gmail.com>

EXPOSE 12345

VOLUME /data

COPY build/libs/*.jar /app/app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
