# STAGE 1: Build
FROM gradle:jdk21 AS builder

WORKDIR /app

COPY ./build.gradle .
COPY ./settings.gradle .
COPY src ./src

RUN gradle build --no-daemon

# STAGE 2: Run
FROM lemuridaelabs/openjdk-java21-jdk:latest

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar discografia.jar

EXPOSE 443

CMD ["java", "-jar", "discografia-1.0.jar"]
