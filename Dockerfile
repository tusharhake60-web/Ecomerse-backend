FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests && ls -la target

CMD ["java", "-jar", "target/E-Commerse-Platform-0.0.1-SNAPSHOT.war"]