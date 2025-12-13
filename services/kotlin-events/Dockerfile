# Stage 1: Build
FROM gradle:8.5-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

# Stage 2: Runtime
FROM amazoncorretto:21-alpine
EXPOSE 8082
MKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/kotlin-events.jar
ENTRYPOINT ["java","-jar","/app/kotlin-events.jar"]