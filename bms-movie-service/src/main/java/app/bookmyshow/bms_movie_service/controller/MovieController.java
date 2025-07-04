package app.bookmyshow.bms_movie_service.controller;

import app.bookmyshow.bms_movie_service.model.Movie;
import app.bookmyshow.bms_movie_service.model.Seat;
import app.bookmyshow.bms_movie_service.model.SeatLockRequest;
import app.bookmyshow.bms_movie_service.model.Show;
import app.bookmyshow.bms_movie_service.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/movies")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @PostMapping("/addMovie")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping("/movies-by-city")
    public ResponseEntity<List<Movie>> getMoviesByCity(@RequestParam String cityName) {
        return ResponseEntity.ok(movieService.getMoviesByCityName(cityName));
    }

    @GetMapping("/movies-by-cinema")
    public ResponseEntity<List<Movie>> getMoviesByCinema(@RequestParam String cinemaName) {
        return ResponseEntity.ok(movieService.getMoviesByCinema(cinemaName));
    }

    @GetMapping("/shows-by-movie-cinema")
    public ResponseEntity<List<Show>> getShowsByMovieCinema(@RequestParam String movieName, @RequestParam String cinemaName) {
        return ResponseEntity.ok(movieService.getShowsByMovieCinema(movieName,cinemaName));
    }

    @GetMapping("/seats-by-show")
    public ResponseEntity<List<Seat>> getSeatsByShow(@RequestParam Integer showId) {
        return ResponseEntity.ok(movieService.getSeatsByShowId(showId));
    }

    @PostMapping("/lock-seats")
    public ResponseEntity<Boolean> lockSeats(@RequestBody SeatLockRequest request) {
        boolean locked = movieService.lockSeats(request.getShowId(), request.getSeats());
        return ResponseEntity.ok(locked);
    }
}
