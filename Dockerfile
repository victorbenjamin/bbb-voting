FROM gradle:4.3-jdk8-alpine

WORKDIR /app

ADD gradle gradle
ADD build.gradle build.gradle
ADD src src
USER root

RUN gradle build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/app-0.0.1-SNAPSHOT.jar"]
