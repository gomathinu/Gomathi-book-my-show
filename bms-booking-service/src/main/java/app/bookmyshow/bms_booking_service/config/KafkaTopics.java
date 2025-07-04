package app.bookmyshow.bms_booking_service.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@Getter
public class KafkaTopics {
    @Value("${kafka.topics.bookingConfirmed}")
    private String bookingConfirmed;
}
