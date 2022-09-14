FROM openjdk:11-jre-buster
MAINTAINER Mario Hercog <mario.hercog@gmail.com>

ARG VERSION
ADD target/gateway-service-${VERSION}.jar /usr/share/gateway-service/gateway-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/share/gateway-service/gateway-service.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8080/actuator/health || exit 1
