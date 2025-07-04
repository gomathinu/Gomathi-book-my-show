package app.bookmyshow.bms_movie_service.service;

import app.bookmyshow.bms_movie_service.model.Movie;
import app.bookmyshow.bms_movie_service.model.Seat;
import app.bookmyshow.bms_movie_service.model.Show;
import app.bookmyshow.bms_movie_service.repository.MovieRepository;
import app.bookmyshow.bms_movie_service.repository.MovieSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieService {

    public List<Movie> getAllMovies();

    public Movie createMovie(Movie movie);

    public Page<Movie> searchMovies(Movie movie);

    public List<Movie> getMoviesByCityName(String cityName);

    public List<Movie> getMoviesByCinema( String cinemaName);

    public List<Show> getShowsByMovieCinema(String movieName, String cinemaName);

    public List<Seat> getSeatsByShowId(Integer showId);

    public boolean lockSeats(Integer showId, List<String> seatNumbersToLock);
}
