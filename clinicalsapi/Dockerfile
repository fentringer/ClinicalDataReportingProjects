
FROM tomcat:9-jdk11-openjdk
VOLUME /tmp
ADD target/clinicalsapi-0.0.1-SNAPSHOT.war clinicalsapi-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "clinicalsapi-0.0.1-SNAPSHOT.war"]
