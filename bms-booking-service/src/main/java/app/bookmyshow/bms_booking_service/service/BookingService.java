package app.bookmyshow.bms_booking_service.service;

import app.bookmyshow.bms_booking_service.model.Booking;

import java.util.Map;

public interface BookingService {
    public Booking initiateBooking(Booking booking);
    public Booking confirmBooking(Integer bookingId);
    public Booking cancelBooking(Integer bookingId);
    public void handlePaymentSuccess(Map<String, Object> event);
    public void handlePaymentFailure(Map<String, Object> event);
}
