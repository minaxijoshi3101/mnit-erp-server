#FROM instruction - It uses base image
FROM openjdk:11.0.2-jre-slim
MAINTAINER snehasish
#COPY erp-%VERSION%.jar .
#COPY instruction is to copy the files or directories from source to destination
COPY target/erp-0.0.1-SNAPSHOT.jar .
# RUN instruction runs the commads written
RUN mkdir -p /home/localfiles
#CMD /usr/bin/java -Xmx400m -Xms400m -jar erp-%VERSION%.jar
# CMD instruction, runs when container is being created
CMD /usr/bin/java -Xmx400m -Xms400m -jar erp-0.0.1-SNAPSHOT.jar
# which port will be used , for example in server.xml connector port is 8085
EXPOSE 8085
