FROM java:8
LABEL maintainer=“harshanand@rsystems.com”
RUN apt-get install -y curl 
EXPOSE 8888
ADD target/ConfigServerApplication-0.0.1-SNAPSHOT.jar ConfigServerApplication-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ConfigServerApplication-0.0.1-SNAPSHOT.jar"]