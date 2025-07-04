package app.bookmyshow.bms_movie_service.service.impl;

import app.bookmyshow.bms_movie_service.model.Movie;
import app.bookmyshow.bms_movie_service.model.Seat;
import app.bookmyshow.bms_movie_service.model.Show;
import app.bookmyshow.bms_movie_service.repository.MovieRepository;
import app.bookmyshow.bms_movie_service.repository.MovieSearchRepository;
import app.bookmyshow.bms_movie_service.repository.CinemaMovieShowRepository;
import app.bookmyshow.bms_movie_service.repository.ShowRepository;
import app.bookmyshow.bms_movie_service.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final MovieSearchRepository movieSearchRepository;
    @Autowired
    private final CinemaMovieShowRepository cinemaMovieShowRepository;
    @Autowired
    private final ShowRepository showRepository;
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }
    @Override
    public Page<Movie> searchMovies(Movie movie){
        return movieSearchRepository.searchMovies(movie);
    }
    @Override public List<Movie> getMoviesByCityName(String cityName){
        return cinemaMovieShowRepository.getMoviesByCityName(cityName);
    }

    @Override
    public List<Movie> getMoviesByCinema(String cinemaName) {
        return cinemaMovieShowRepository.getMoviesByCinemaName(cinemaName);
    }

    @Override
    public List<Show> getShowsByMovieCinema(String movieName, String cinemaName) {
        return cinemaMovieShowRepository.getShowsByMovieAndCinema(movieName, cinemaName);
    }

    @Override
    public List<Seat> getSeatsByShowId(Integer showId) {
        return cinemaMovieShowRepository.getSeatsByShowId(showId);
    }

    @Override
    public boolean lockSeats(Integer showId, List<String> seatNumbersToLock) {
        Optional<Show> showOpt = showRepository.findById(showId);
        if (showOpt.isEmpty()) return false;
        Show show = showOpt.get();
        List<Seat> seats = show.getSeatList();
        // Check availability and lock
        List<Seat> updatedSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (seatNumbersToLock.contains(seat.getSeatNumber())) {
                if (!seat.isAvailable()) {
                    // Seat already booked
                    return false;
                }
                // Lock seat
                seat.setAvailable(false);
            }
            updatedSeats.add(seat);
        }
        show.setSeatList(updatedSeats);
        showRepository.save(show);
        return true;
    }
}
