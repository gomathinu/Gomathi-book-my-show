
package app.bookmyshow.bms_movie_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "show")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {
    @Id
    private String _id;
    private String showId;
    private LocalDateTime startTime;
    private List<Seat> seatList;
    @Transient
    private Movie movie;
    private String movieId;
    @Transient
    private Cinema cinema;
    private String cinemaId;
}
