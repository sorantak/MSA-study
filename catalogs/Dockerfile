FROM java:8
EXPOSE 8081
ADD ./target/catalogs-0.0.1-SNAPSHOT.jar catalog.jar
ENTRYPOINT ["java", "-jar", "catalog.jar"]