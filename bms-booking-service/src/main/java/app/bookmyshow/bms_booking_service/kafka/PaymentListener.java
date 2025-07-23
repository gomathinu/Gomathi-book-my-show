package app.bookmyshow.bms_booking_service.kafka;

import app.bookmyshow.bms_booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentListener {

    private static final Logger log = LoggerFactory.getLogger(PaymentListener.class);

    private final BookingService bookingService;

    @KafkaListener(topics = "payment-success", groupId = "payment-group")
    public void onPaymentSuccess(Map<String, Object> event) {
        log.info("PaymentListener: onPaymentSuccess() is called");
        bookingService.handlePaymentSuccess(event);
    }

    @KafkaListener(topics = "payment-failed", groupId = "payment-group")
    public void onPaymentFailure(Map<String, Object> event) {
        log.info("PaymentListener: onPaymentFailure() is called");
        bookingService.handlePaymentFailure(event);
    }
}
