FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER xxx@163.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

#RUN mkdir -p /config/brightcomplete/

WORKDIR /cv-spring

EXPOSE 8080

#ADD ./src/main/resources/brightcomplete ./config/brightcomplete
ADD ./target/cv-system-start-1.0.0.jar ./

CMD sleep 60;java -Djava.security.egd=file:/dev/./urandom -jar cv-system-start-1.0.0.jar