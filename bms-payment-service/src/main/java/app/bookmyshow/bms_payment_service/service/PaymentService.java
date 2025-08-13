package app.bookmyshow.bms_payment_service.service;

import app.bookmyshow.bms_payment_service.model.Booking;
import app.bookmyshow.bms_payment_service.model.Payment;

import java.util.List;

public interface PaymentService {
    public Payment initiatePayment(Booking booking, String token);
    public List<Payment> getPaymentsForUser(String userId);
}
