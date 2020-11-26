FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY target/*.jar /app/booking_seats_cinema.jar

EXPOSE 8888
ENTRYPOINT ["java","-jar","/app/booking_seats_cinema.jar"]
