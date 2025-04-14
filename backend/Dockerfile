# Usar una imagen base con Maven y JDK 17
FROM maven:3.9.4-amazoncorretto-17 AS build

# Establecer el directorio de trabajo para construir
WORKDIR /app

# Copiar los archivos del proyecto
COPY . .

# Construir el proyecto y omitir los tests
RUN mvn clean package -DskipTests

# Segunda etapa: imagen más liviana solo con el JAR
FROM amazoncorretto:17

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la app
CMD ["java", "-jar", "app.jar"]
