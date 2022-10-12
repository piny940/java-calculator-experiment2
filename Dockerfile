FROM adoptopenjdk/openjdk8

RUN apt-get -y update && \
  apt-get install -y sudo wget vim git curl
