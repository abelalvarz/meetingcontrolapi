FROM gradle:8.3-jdk17 AS build

WORKDIR /home/app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

RUN ./gradlew build --no-daemon || return 0

COPY src ./src

RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jdk-alpine

COPY --from=build /home/app/build/libs/MeetingControlApi-0.0.1-SNAPSHOT.jar /usr/local/lib/MeetingControlApi.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/lib/MeetingControlApi.jar"]
