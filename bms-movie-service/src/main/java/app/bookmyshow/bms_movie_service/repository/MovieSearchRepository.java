package app.bookmyshow.bms_movie_service.repository;

import app.bookmyshow.bms_movie_service.model.Movie;
import org.springframework.data.domain.Page;

public interface MovieSearchRepository {
    Page<Movie> searchMovies(Movie request);
}
