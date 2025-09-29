#!/bin/bash

echo "Esperando $SLEEP_LENGTH segundos antes de arrancar la app..."
sleep $SLEEP_LENGTH

echo "MySQL arriba, ejecutando Liquibase con Maven..."

# Ejecuta Liquibase con Maven
./mvnw liquibase:update \
  -Dliquibase.url="jdbc:mysql://$MYSQL_HOST:3306/$MYSQL_DATABASE?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC" \
  -Dliquibase.username="$MYSQL_USER" \
  -Dliquibase.password="$MYSQL_PASSWORD" \
  -Dliquibase.changeLogFile="src/main/resources/db/changelog/changelog.xml" \
  -Dliquibase.driver="com.mysql.cj.jdbc.Driver"

echo "Liquibase ejecutado correctamente."

