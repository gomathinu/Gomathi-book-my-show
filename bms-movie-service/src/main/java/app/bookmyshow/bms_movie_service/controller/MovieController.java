package app.bookmyshow.bms_movie_service.controller;

import app.bookmyshow.bms_movie_service.model.Movie;
import app.bookmyshow.bms_movie_service.model.Seat;
import app.bookmyshow.bms_movie_service.model.SeatLockRequest;
import app.bookmyshow.bms_movie_service.model.Show;
import app.bookmyshow.bms_movie_service.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieDetails")
@RequiredArgsConstructor
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private final MovieService movieService;

    //API for adding a movie by admin user
    @PostMapping("/addMovie")
    public Movie createMovie(@RequestBody Movie movie) {
        log.info("MovieController: createMovie() is called");
        return movieService.createMovie(movie);
    }

    @GetMapping("/movies-by-city")
    public ResponseEntity<List<Movie>> getMoviesByCity(@RequestParam String cityName) {
        log.info("MovieController: getMoviesByCity() is called");
        return ResponseEntity.ok(movieService.getMoviesByCityName(cityName));
    }

    @GetMapping("/movies-by-cinema")
    public ResponseEntity<List<Movie>> getMoviesByCinema(@RequestParam String cinemaName) {
        log.info("MovieController: getMoviesByCinema() is called");
        return ResponseEntity.ok(movieService.getMoviesByCinema(cinemaName));
    }

    @GetMapping("/shows-by-movie-cinema")
    public ResponseEntity<List<Show>> getShowsByMovieCinema(@RequestParam String movieName, @RequestParam String cinemaName) {
        log.info("MovieController: getShowsByMovieCinema() is called");
        return ResponseEntity.ok(movieService.getShowsByMovieCinema(movieName,cinemaName));
    }

    @GetMapping("/seats-by-show")
    public ResponseEntity<List<Seat>> getSeatsByShow(@RequestParam Integer showId) {
        log.info("MovieController: getSeatsByShow() is called");
        return ResponseEntity.ok(movieService.getSeatsByShowId(showId));
    }

    @PostMapping("/lock-seats")
    public ResponseEntity<Boolean> lockSeats(@RequestBody SeatLockRequest request) {
        log.info("MovieController: lockSeats() is called");
        boolean locked = movieService.lockSeats(request.getShowId(), request.getSeats());
        return ResponseEntity.ok(locked);
    }
}
