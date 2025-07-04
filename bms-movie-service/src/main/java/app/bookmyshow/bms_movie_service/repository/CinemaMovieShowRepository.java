package app.bookmyshow.bms_movie_service.repository;

import app.bookmyshow.bms_movie_service.model.Movie;
import app.bookmyshow.bms_movie_service.model.Seat;
import app.bookmyshow.bms_movie_service.model.Show;

import java.util.List;

public interface CinemaMovieShowRepository {
    public List<Movie> getMoviesByCityName(String cityName);
    public List<Movie> getMoviesByCinemaName(String cinemaName);
    public List<Show> getShowsByMovieAndCinema(String movieName, String cinemaName);
    public List<Seat> getSeatsByShowId(Integer showId);
}
