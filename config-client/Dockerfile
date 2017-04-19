FROM java:8-jre
MAINTAINER elviszhang <zhangb2002@163.com>

ADD ./target/config-client.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-client.jar.jar"]

EXPOSE 9000