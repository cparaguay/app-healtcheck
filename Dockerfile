FROM openjdk:8-slim-buster
WORKDIR /workspace
LABEL maintainer=cristhianp00@gmail.com
ENV DOCKERIZE_VERSION v0.6.1

RUN apt-get update && apt-get install -y wget && apt-get install -y curl

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY target/app.jar /workspace/app.jar
CMD java -jar app.jar

