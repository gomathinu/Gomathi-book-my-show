package app.bookmyshow.bms_booking_service.service.impl;

import app.bookmyshow.bms_booking_service.enumModel.BookingStatus;
import app.bookmyshow.bms_booking_service.config.KafkaTopics;
import app.bookmyshow.bms_booking_service.model.Booking;
import app.bookmyshow.bms_booking_service.repository.BookingRepository;
import app.bookmyshow.bms_booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    private final BookingRepository repository;
    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private final KafkaTopics topics;

    @Override
    public Booking initiateBooking(Booking booking,String token) {
        //Lock seats via Movie Service
        //TODO: While locking seats could introduce timer
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(
                Map.of("showId", booking.getShowId(), "seats", booking.getSeatNumbers()),
                headers
        );
        var response = restTemplate.postForEntity(
                "http://bms-api-gateway-service:8080/bms/movie/movieDetails/lock-seats",
                requestEntity,
                Boolean.class
        );
        if (Boolean.FALSE.equals(response.getBody())) {
            log.error("Seats not available");
            throw new RuntimeException("Seats not available");
        }
        //Create booking with status PENDING
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());
        return repository.save(booking);
    }

    @Override
    public Booking confirmBooking(String bookingId) {
        var booking = repository.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.CONFIRMED);
        return repository.save(booking);
    }

    @Override
    public Booking cancelBooking(String bookingId) {
        var booking = repository.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        return repository.save(booking);
    }

    @Override
    public void handlePaymentSuccess(Map<String, Object> event) {
        String bookingId = (String) event.get("bookingId");
        String userId = (String) event.get("userId");
        String token = (String) event.get("token");
        confirmBooking(bookingId);
        // Notify messaging service
        Map<String, Object> bookingDetailsRequest = Map.of(
                "bookingId", bookingId,
                "userId", userId,
                "token", token
        );
        kafkaTemplate.send(topics.getBookingConfirmed(), bookingDetailsRequest);
    }

    @Override
    public void handlePaymentFailure(Map<String, Object> event) {
        String bookingId = (String) event.get("bookingId");
        cancelBooking(bookingId);
    }
}
