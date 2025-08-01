package app.bookmyshow.bms_payment_service.model;

import app.bookmyshow.bms_payment_service.enumModel.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    private String paymentId;
    private String bookingId;
    private String userId;
    private Double amount;
    private PaymentStatus status;
    private String method;
    private LocalDateTime timestamp;
}