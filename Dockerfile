FROM maven:3.6-jdk-8-alpine AS BUILD

RUN ["mkdir", "/usr/local"]
COPY . /usr/local/
WORKDIR /usr/local/
RUN ["mvn", "package"]

FROM tomcat:9.0.16-jre8-alpine

RUN ["mkdir", "/usr/local/tomcat/webapps/Gamma"]

EXPOSE 8080
COPY --from=BUILD /usr/local/target/Gamma/ /usr/local/tomcat/webapps/Gamma/