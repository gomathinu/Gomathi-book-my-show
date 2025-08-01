package app.bookmyshow.bms_payment_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    private static final String MOBILE_HEADER = "X-Mobile";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String correlationId = request.getHeader(CORRELATION_ID_HEADER);
            if (correlationId != null) {
                MDC.put(CORRELATION_ID_HEADER, correlationId);
            }
            String mobile = request.getHeader(MOBILE_HEADER);
            MDC.put(MOBILE_HEADER, mobile);
            MDC.put("serviceName", "bms-payment-service");
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}

