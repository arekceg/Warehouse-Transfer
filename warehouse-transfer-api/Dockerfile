FROM openjdk:11.0.3-jdk-slim-stretch
ADD ./target/wt-api.war /app/
ADD run.sh /run.sh
RUN chmod +x /run.sh
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.5.0/wait /wait
RUN chmod +x /wait
CMD /wait && /run.sh
EXPOSE 8081
