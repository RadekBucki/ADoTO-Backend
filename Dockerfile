FROM ringcentral/maven:3.8.2-jdk17 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:17-oracle

COPY --from=builder /usr/src/app/target/ADOTO.jar /usr/app/ADOTO.jar

ENTRYPOINT ["java", "-jar", "/usr/app/ADOTO.jar"]