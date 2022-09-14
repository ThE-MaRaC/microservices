FROM openjdk:11-jre-buster
MAINTAINER Mario Hercog <mario.hercog@gmail.com>

COPY config-data /root/config-data

ARG VERSION
ADD target/config-server-${VERSION}.jar /usr/share/config-server/config-server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/usr/share/config-server/config-server.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8080/actuator/health || exit 1
