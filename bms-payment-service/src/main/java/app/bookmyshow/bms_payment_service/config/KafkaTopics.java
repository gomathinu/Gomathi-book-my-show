package app.bookmyshow.bms_payment_service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KafkaTopics {
    @Value("${kafka.topics.paymentSuccess}")
    private String paymentSuccess;

    @Value("${kafka.topics.paymentFailed}")
    private String paymentFailed;
}
