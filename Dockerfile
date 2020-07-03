FROM openjdk:8-jdk

ENV TZ=Europe/Berlin
ENV SERVER_PORT 8080

EXPOSE $SERVER_PORT

RUN mkdir /app
WORKDIR /app

RUN useradd --no-log-init --no-create-home --shell /bin/false service_user

# Arguments
ARG SOURCE_JAR_FILE="target/*.jar"
ARG BUILD_DATE
ARG VCS_REF

# Labels
LABEL org.label-schema.schema-version="1.0"
LABEL org.label-schema.build-date=$BUILD_DATE
LABEL org.label-schema.vcs-ref=$VCS_REF

COPY $SOURCE_JAR_FILE app.jar

RUN sh -c "touch app.jar"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
