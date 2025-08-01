package app.bookmyshow.bms_api_gateway_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class CorrelationIdFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(CorrelationIdFilter.class);
    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    private static final String MOBILE_HEADER = "X-Mobile";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String correlationId = request.getHeaders().getFirst(CORRELATION_ID_HEADER);
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        String mobile = request.getHeaders().getFirst(MOBILE_HEADER);
        // Add correlation id to MDC Mapped Diagnostic Context for logging for identifying each service in a call trace
        MDC.put(CORRELATION_ID_HEADER, correlationId);
        MDC.put(MOBILE_HEADER, mobile);
        MDC.put("serviceName", "bms-api-gateway-service");
        ServerHttpRequest mutatedRequest = request.mutate()
                .header(CORRELATION_ID_HEADER, correlationId)
                .header(MOBILE_HEADER, mobile)
                .build();
        return chain.filter(exchange.mutate().request(mutatedRequest).build())
                .doFinally(signal -> MDC.clear());
    }
    //just overriding
    @Override
    public int getOrder() {
        return -1;
    }
}
