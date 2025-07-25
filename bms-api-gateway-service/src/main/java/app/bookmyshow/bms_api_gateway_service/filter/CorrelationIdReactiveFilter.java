package app.bookmyshow.bms_api_gateway_service.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CorrelationIdReactiveFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //Access MDC through ContextView - for api gateway
        return Mono.deferContextual(contextView -> {
            contextView.stream()
                    .forEach(entry -> MDC.put(entry.getKey().toString(), String.valueOf(entry.getValue())));
            return chain.filter(exchange)
                    .doFinally(signalType -> MDC.clear()); // clear to avoid leakage across requests
        });
    }
}
