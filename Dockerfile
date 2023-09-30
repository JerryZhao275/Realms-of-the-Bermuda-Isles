FROM openjdk:17
COPY . /tmp
WORKDIR /tmp
CMD ./gradlew shadowJar
CMD java -jar build/libs/comp-2120-assignment-3-workshop-07-group-a-1.0-SNAPSHOT-all.jar
#ENTRYPOINT ["java","MainMenu"]