FROM openjdk:12
VOLUME /tmp
ADD ./build/libs/persist-report-0.0.1-SNAPSHOT.jar persist-report.jar
ENTRYPOINT ["java", "-jar" , "/persist-report.jar"]