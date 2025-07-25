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

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String correlationId = request.getHeader("X-Correlation-ID");
            if (correlationId != null) {
                MDC.put("X-Correlation-ID", correlationId);
            }
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}

