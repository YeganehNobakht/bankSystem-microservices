FROM openjdk:17-jdk-slim

#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="masi.yeganeh"

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]