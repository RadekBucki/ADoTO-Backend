FROM ringcentral/maven:3.8.2-jdk17

WORKDIR /adoto-backend
COPY /target/ADOTO.jar .

ENTRYPOINT ["java","-jar","ADOTO.jar"]