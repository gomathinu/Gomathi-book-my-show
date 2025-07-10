package app.bookmyshow.bms_messaging_service.service;

public interface MessagingService {
    public void sendBookingSuccessMessage(Integer bookingId, String userId);
}
