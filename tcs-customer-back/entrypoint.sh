#!/bin/bash
echo "Esperando $SLEEP_LENGTH segundos antes de arrancar la app..."
sleep $SLEEP_LENGTH

echo "Arrancando aplicación..."
exec java -jar /root/target/tcs-customer-back-0.0.1-SNAPSHOT.jar