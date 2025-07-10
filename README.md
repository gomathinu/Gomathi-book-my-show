1.Please download project from github
2.After setting up project along with its requirements like having Apache maven, docker in your local, please do following to build and run the services
mvn clean package  (all backend microservices)
docker-compose build
docker-compose up

Angular Frontend: http://localhost:4200

API Gateway: http://localhost:8080

Eureka Dashboard: http://localhost:8761

(TODO: Include each service related info and postman json)
