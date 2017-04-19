FROM java:8-jre
MAINTAINER elviszhang <zhangb2002@163.com>

ADD ./target/config-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-server.jar"]

EXPOSE 8888