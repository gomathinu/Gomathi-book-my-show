package app.bookmyshow.bms_movie_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatLockRequest {
    private String showId;
    private List<String> seats;
}
