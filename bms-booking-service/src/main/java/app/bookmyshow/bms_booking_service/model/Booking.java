package app.bookmyshow.bms_booking_service.model;

import app.bookmyshow.bms_booking_service.enumModel.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    private String bookingId;
    private String userId;
    private String showId;
    private List<String> seatNumbers;
    private Double totalAmount;
    private BookingStatus status;
    private LocalDateTime createdAt;
}
