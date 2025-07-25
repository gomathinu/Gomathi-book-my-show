The project is building Book m show application with Java, Spring boot, Microservices, with Angular as Frontend.

To run this project in local, please follow below steps,

1.Please clone project from github into your local
2.After setting up project along with its requirements like having Apache maven, Java, Docker in your local, please do following to build and run the services
mvn clean package  (each of all backend microservices)
docker-compose build
docker-compose up

Angular Frontend: http://localhost:4200

API Gateway: http://localhost:8080

Eureka Dashboard: http://localhost:8761

Swagger apis for each of services:
Movie: http://localhost:8082/swagger-ui/index.html
Booking: 8083
Payment: 8085

To run in postman, please use below to generate token
http://localhost:8081/bms/user/userDetails/getToken
{
"mobile":"9876543210",
"otp":"123"
}
Copy that token into your postman environment as jwt_token, for all post-login services

(Could have Mongo compass and data in bookmyshow db and collections in it to run services in local with data)