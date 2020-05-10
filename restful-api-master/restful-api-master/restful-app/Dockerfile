FROM openjdk:8
ADD target/rest-service-0.0.1-SNAPSHOT.jar rest-service-0.0.1-SNAPSHOT.jar 
EXPOSE 8085
ENTRYPOINT ["java","-jar","rest-service-0.0.1-SNAPSHOT.jar"]
COPY ./contents.json contents.json