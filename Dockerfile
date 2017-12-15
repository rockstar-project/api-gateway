FROM ibmcom/ibmjava
MAINTAINER ibmcloud bluemix

ADD target/api-gateway-1.0.0-SNAPSHOT.jar api-gateway.jar
EXPOSE 8765
RUN bash -c 'touch /api-gateway.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/api-gateway.jar"]