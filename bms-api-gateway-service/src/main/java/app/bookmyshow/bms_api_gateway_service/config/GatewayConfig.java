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
                .route("movie_service", r -> r.path("/api/movie/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8082"))
                .route("booking_service", r -> r.path("/api/booking/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8083"))
                .route("messaging_service", r -> r.path("/api/messaging/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8084"))
                .route("payment_service", r -> r.path("/api/payment/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8085"))
                .build();
    }
}
