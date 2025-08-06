package app.bookmyshow.bms_booking_service.service;

import app.bookmyshow.bms_booking_service.model.Booking;

import java.util.Map;

public interface BookingService {
    public Booking initiateBooking(Booking booking,String token);
    public Booking confirmBooking(String bookingId);
    public Booking cancelBooking(String bookingId);
    public void handlePaymentSuccess(Map<String, Object> event);
    public void handlePaymentFailure(Map<String, Object> event);
}
