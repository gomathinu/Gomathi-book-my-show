package app.bookmyshow.bms_api_gateway_service.config;

import app.bookmyshow.bms_api_gateway_service.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                //Prelogin urls
                .route("user_service_public", r -> r.path(
                                "/bms/user/userDetails/getToken",
                                "/bms/user/userDetails/login",
                                "/bms/user/userDetails/register",
                                "/bms/user/userDetails/verify-otp")
                        .filters(f -> f.rewritePath("/bms/user/(?<path>.*)", "/${path}"))
                        .uri("http://bms-user-service:8081"))
                //Postlogin urls
                .route("user_service", r -> r.path(
                                "/bms/user/userDetails/getUserDetails")
                        .filters(f -> f.rewritePath("/bms/user/(?<path>.*)", "/${path}")
                                .filter(jwtFilter))
                        .uri("http://bms-user-service:8081"))
                .route("movie_service", r -> r.path("/bms/movie/**")
                        .filters(f -> f.rewritePath("/bms/movie/(?<path>.*)", "/${path}")
                                .filter(jwtFilter))
                        .uri("http://bms-movie-service:8082"))
                .route("booking_service", r -> r.path("/bms/booking/**")
                        .filters(f -> f.rewritePath("/bms/booking/(?<path>.*)", "/${path}")
                                .filter(jwtFilter))
                        .uri("http://bms-booking-service:8083"))
                .route("messaging_service", r -> r.path("/bms/messaging/**")
                        .filters(f -> f.rewritePath("/bms/messaging/(?<path>.*)", "/${path}")
                                .filter(jwtFilter))
                        .uri("http://bms-messaging-service:8084"))
                .route("payment_service", r -> r.path("/bms/payment/**")
                        .filters(f -> f.rewritePath("/bms/payment/(?<path>.*)", "/${path}")
                                .filter(jwtFilter))
                        .uri("http://bms-payment-service:8085"))
                .build();
    }
}
