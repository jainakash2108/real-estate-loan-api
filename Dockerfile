FROM eclipse-temurin:17-alpine
VOLUME /tmp
ADD target/real-estate-loan-api-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8089