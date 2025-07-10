package app.bookmyshow.bms_payment_service.controller;

import app.bookmyshow.bms_payment_service.model.Payment;
import app.bookmyshow.bms_payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentDetails")
@RequiredArgsConstructor
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private final PaymentService paymentService;

    @PostMapping("/initiatePayment")
    public ResponseEntity<Payment> initiatePayment(@RequestBody Payment payment) {
        log.info("PaymentController: initiatePayment() is called");
        return ResponseEntity.ok(paymentService.initiatePayment(payment));
    }
}
