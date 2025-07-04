package app.bookmyshow.bms_booking_service.controller;

import app.bookmyshow.bms_booking_service.model.Booking;
import app.bookmyshow.bms_booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bms/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/initiate")
    public ResponseEntity<Booking> initiate(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.initiateBooking(booking));
    }

    @PostMapping("/confirm/{bookingId}")
    public ResponseEntity<Booking> confirm(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(bookingService.confirmBooking(bookingId));
    }

    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<Booking> cancel(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
}
