FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim

WORKDIR /opt

ENV PORT 8080
EXPOSE 8080

COPY ./build/libs/ms-speakers-*-SNAPSHOT.jar /opt/app.jar

ENV JAVA_OPTS "-XX:+UseContainerSupport -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=70"

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar