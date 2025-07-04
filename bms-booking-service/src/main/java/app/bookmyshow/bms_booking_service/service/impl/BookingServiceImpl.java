package app.bookmyshow.bms_booking_service.service.impl;

import app.bookmyshow.bms_booking_service.enumModel.BookingStatus;
import app.bookmyshow.bms_booking_service.config.KafkaTopics;
import app.bookmyshow.bms_booking_service.model.Booking;
import app.bookmyshow.bms_booking_service.repository.BookingRepository;
import app.bookmyshow.bms_booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repository;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaTopics topics;

    @Override
    public Booking initiateBooking(Booking booking) {
        //Lock seats via Movie Service
        //TODO: While locking seats could introduce timer
        var response = restTemplate.postForEntity(
                "http://localhost:8082//bms/movies/lock-seats",
                Map.of("showId", booking.getShowId(), "seats", booking.getSeatNumbers()),
                Boolean.class
        );
        if (Boolean.FALSE.equals(response.getBody())) {
            throw new RuntimeException("Seats not available");
        }
        //Create booking with status PENDING
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        return repository.save(booking);
    }

    @Override
    public Booking confirmBooking(Integer bookingId) {
        var booking = repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.CONFIRMED);
        return repository.save(booking);
    }

    @Override
    public Booking cancelBooking(Integer bookingId) {
        var booking = repository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        return repository.save(booking);
    }

    @Override
    public void handlePaymentSuccess(Map<String, Object> event) {
        Integer bookingId = (Integer) event.get("bookingId");
        confirmBooking(bookingId);
        // Notify messaging service
        //TODO: Introduce Kafka listener in Messaging service (for success case), could intro later for failure case too
        kafkaTemplate.send(topics.getBookingConfirmed(), event);
    }

    @Override
    public void handlePaymentFailure(Map<String, Object> event) {
        Integer bookingId = (Integer) event.get("bookingId");
        cancelBooking(bookingId);
    }
}
