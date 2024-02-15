# Docker Build Maven Stage
FROM maven:3-jdk-8-alpine AS build
# Copy folder in docker
WORKDIR /opt/app
COPY ./ /opt/app
RUN mvn clean package
# Run spring boot in Docker
FROM openjdk:8-jdk-alpine
COPY --from=build /opt/app/target/*.jar app.jar
ENV PORT 8081
EXPOSE $PORT
ARG envname
ENV ACTIVE_PROFILE $envname
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${ACTIVE_PROFILE}","-Dserver.port=${PORT}","app.jar"]