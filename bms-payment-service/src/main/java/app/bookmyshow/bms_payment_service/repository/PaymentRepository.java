package app.bookmyshow.bms_payment_service.repository;

import app.bookmyshow.bms_payment_service.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Integer> {
    List<Payment> findByUserId(String userId);
}
