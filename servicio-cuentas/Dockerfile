FROM maven:3.8-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

COPY servicio-clientes/pom.xml ./servicio-clientes/
COPY servicio-cuentas/pom.xml ./servicio-cuentas/

COPY servicio-cuentas/src ./servicio-cuentas/src/

RUN mvn -f servicio-cuentas/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-focal

WORKDIR /app

COPY --from=build /app/servicio-cuentas/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]