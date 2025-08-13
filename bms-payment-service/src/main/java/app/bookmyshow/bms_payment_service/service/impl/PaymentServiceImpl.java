package app.bookmyshow.bms_payment_service.service.impl;

import app.bookmyshow.bms_payment_service.config.KafkaTopics;
import app.bookmyshow.bms_payment_service.enumModel.PaymentStatus;
import app.bookmyshow.bms_payment_service.model.Booking;
import app.bookmyshow.bms_payment_service.model.Payment;
import app.bookmyshow.bms_payment_service.repository.PaymentRepository;
import app.bookmyshow.bms_payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private final KafkaTopics topics;

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public Payment initiatePayment(Booking booking, String token) {
        Payment payment = new Payment();
        payment.setPaymentId(String.valueOf(Math.random()));
        payment.setBookingId(booking.getBookingId());
        payment.setUserId(booking.getUserId());
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setTimestamp(LocalDateTime.now());
        boolean success = checkPaymentStatusFromPaymentGateway(payment);
        PaymentStatus finalStatus = success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
        payment.setStatus(finalStatus);
        Payment saved = paymentRepository.save(payment);
        log.debug("Payment data to Kafka service: paymentId:{}",saved.getPaymentId());
        log.debug("Payment data to Kafka service: bookingId:{}",saved.getBookingId());
        log.debug("Payment data to Kafka service: userId:{}",saved.getUserId());
        log.debug("Payment data to Kafka service: amount:{}",saved.getAmount());
        log.debug("Payment data to Kafka service: status:{}",saved.getStatus().toString());
        log.debug("Payment data to Kafka service: timestamp:{}",saved.getTimestamp());
        log.debug("Payment data to Kafka service: token:{}",token);
        //Form kafka service request
        Map<String, Object> event = Map.of(
                "paymentId", saved.getPaymentId(),
                "bookingId", saved.getBookingId(),
                "userId", saved.getUserId(),
                "amount", saved.getAmount(),
                "status", saved.getStatus().toString(),
                "timestamp", saved.getTimestamp(),
                "token", token
        );
        if (success) {
            log.debug("Payment status is success");
            kafkaTemplate.send(topics.getPaymentSuccess(), event);
        } else {
            log.debug("Payment is failed");
            kafkaTemplate.send(topics.getPaymentFailed(), event);
        }
        return saved;
    }

    private boolean checkPaymentStatusFromPaymentGateway(Payment payment) {
        //Hardcoding payment status to be true here for this
        return true;
    }

    @Override
    public List<Payment> getPaymentsForUser(String userId) {
        return paymentRepository.findByUserId(userId);
    }
}
