package app.bookmyshow.bms_booking_service.kafka;

import app.bookmyshow.bms_booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final BookingService bookingService;

    @KafkaListener(topics = "payment-success", groupId = "booking-group")
    public void onPaymentSuccess(Map<String, Object> event) {
        bookingService.handlePaymentSuccess(event);
    }

    @KafkaListener(topics = "payment-failed", groupId = "booking-group")
    public void onPaymentFailure(Map<String, Object> event) {
        bookingService.handlePaymentFailure(event);
    }
}
