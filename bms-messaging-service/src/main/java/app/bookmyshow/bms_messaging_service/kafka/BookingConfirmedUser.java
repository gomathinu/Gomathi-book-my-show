package app.bookmyshow.bms_messaging_service.kafka;

import app.bookmyshow.bms_messaging_service.service.MessagingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class BookingConfirmedUser {

    private static final Logger log = LoggerFactory.getLogger(BookingConfirmedUser.class);

    @Autowired
    private final MessagingService messagingService;

    @KafkaListener(topics = "booking-confirmed", groupId = "notification-group")
    public void consume(String message) {
        log.info("Received booking confirmed message: {}", message);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(message);
            Integer bookingId = node.get("bookingId").asInt();
            String userId = node.get("userId").toString();
            messagingService.sendBookingSuccessMessage(bookingId, userId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
