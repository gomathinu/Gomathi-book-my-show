package app.bookmyshow.bms_booking_service.controller;

import app.bookmyshow.bms_booking_service.model.Booking;
import app.bookmyshow.bms_booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookingDetails")
@RequiredArgsConstructor
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private final BookingService bookingService;

    @PostMapping("/initiateBooking")
    public ResponseEntity<Booking> initiateBooking(@RequestBody Booking booking,@RequestHeader("Authorization") String token) {
        log.info("BookingController: initiateBooking() is called");
        return ResponseEntity.ok(bookingService.initiateBooking(booking,token));
    }
}
