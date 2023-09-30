FROM openjdk:17
COPY ./out/production/comp-2120-assignment-3-workshop-07-group-a/ /tmp
WORKDIR /tmp
CMD java MainMenu
#ENTRYPOINT ["java","MainMenu"]