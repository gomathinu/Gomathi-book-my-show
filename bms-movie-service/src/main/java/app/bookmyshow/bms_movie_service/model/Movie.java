
package app.bookmyshow.bms_movie_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    private String _id;
    private String movieId;
    private String title;
    private String genre;
    private String language;
    private String description;
    private List<String> cast;
    private List<String> crew;
    private LocalDate releaseAfter;
    private int page = 0;
    private int size = 10;
    private String sortBy = "releaseDate";
    private String sortDir = "asc";
    private String posterUrl;
}
