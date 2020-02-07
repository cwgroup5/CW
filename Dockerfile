FROM openjdk:latest
COPY ./target/cw.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "cw.jar", "db:3305"]