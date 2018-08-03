FROM openjdk:8-jdk
MAINTAINER <zvezdo4eet@gmail.com>

EXPOSE 12345

VOLUME /data

COPY build/libs/*.jar /app/service.jar

CMD ["java", "-jar -Dspring.profiles.active=production", "/app/service.jar"]