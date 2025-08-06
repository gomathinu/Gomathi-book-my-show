#!/bin/bash

# List of microservice directories
services=(
  "bms-api-gateway-service"
  "bms-user-service"
  "bms-movie-service"
  "bms-booking-service"
  "bms-payment-service"
  "bms-messaging-service"
  "bms-service-discovery"
)

echo "Building all backend microservices..."
for service in "${services[@]}"
do
  echo "-----------------------------"
  echo "Building $service..."
  cd "$service" || exit
  mvn clean package -DskipTests
  cd ..
done