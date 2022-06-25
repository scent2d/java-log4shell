FROM openjdk:11 as intermediate
RUN apt-get update
RUN apt-get install -y maven
WORKDIR /log4shell
COPY src /log4shell/src
COPY pom.xml /log4shell/pom.xml
RUN mvn package

FROM tomcat:9.0.1-jre8-alpine 
COPY --from=intermediate /log4shell/target/ /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
EXPOSE 8080