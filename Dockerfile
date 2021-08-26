FROM openjdk:11.0.2-jre-slim
MAINTAINER snehasish
#COPY erp-%VERSION%.jar .
COPY target/erp-0.0.1-SNAPSHOT.jar .
RUN mkdir -p /home/localfiles
#CMD /usr/bin/java -Xmx400m -Xms400m -jar erp-%VERSION%.jar
CMD /usr/bin/java -Xmx400m -Xms400m -jar erp-0.0.1-SNAPSHOT.jar
EXPOSE 8085