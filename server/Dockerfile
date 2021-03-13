FROM gradle:6.7-jdk8

COPY . /game-server
RUN rm -rf /game-server
WORKDIR /game-server
RUN gradle build --exclude-task test

ENTRYPOINT ["gradle", "run"]
