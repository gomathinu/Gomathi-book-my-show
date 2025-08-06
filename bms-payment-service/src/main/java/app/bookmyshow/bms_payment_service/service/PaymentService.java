package app.bookmyshow.bms_payment_service.service;

import app.bookmyshow.bms_payment_service.model.Payment;

import java.util.List;

public interface PaymentService {
    public Payment initiatePayment(Payment payment,String token);
    public List<Payment> getPaymentsForUser(String userId);
}
