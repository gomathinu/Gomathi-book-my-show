package app.bookmyshow.bms_movie_service.repository.impl;

import app.bookmyshow.bms_movie_service.model.Movie;
import app.bookmyshow.bms_movie_service.model.Seat;
import app.bookmyshow.bms_movie_service.model.Show;
import app.bookmyshow.bms_movie_service.repository.CinemaMovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CinemaMovieShowRepositoryImpl implements CinemaMovieShowRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> getMoviesByCityName(String cityName) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("cityName").is(cityName)),
                LookupOperation.newLookup().from("cinema").localField("cityId").foreignField("cityId").as("cinemas"),
                Aggregation.unwind("cinemas"),
                LookupOperation.newLookup().from("show").localField("cinemas.cinemaId").foreignField("cinemaId").as("shows"),
                Aggregation.unwind("shows"),
                LookupOperation.newLookup().from("movie").localField("shows.movieId").foreignField("movieId").as("movieDetails"),
                Aggregation.unwind("movieDetails"),
                Aggregation.group("movieDetails.movieId").first("movieDetails").as("movie"),
                Aggregation.replaceRoot("movie")
        );
        return mongoTemplate.aggregate(aggregation, "city", Movie.class).getMappedResults();
    }

    @Override
    public List<Movie> getMoviesByCinemaName(String cinemaName) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("cinemaName").is(cinemaName)),
                Aggregation.lookup("show", "cinemaId", "cinemaId", "shows"),
                Aggregation.unwind("shows"),
                Aggregation.lookup("movie", "shows.movieId", "movieId", "movieDetails"),
                Aggregation.unwind("movieDetails"),
                Aggregation.group("movieDetails.movieId").first("movieDetails").as("movie"),
                Aggregation.replaceRoot("movie")
        );
        return mongoTemplate.aggregate(aggregation, "cinema", Movie.class).getMappedResults();
    }

    @Override
    public List<Show> getShowsByMovieAndCinema(String movieName, String cinemaName) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("cinemaName").is(cinemaName)),
                Aggregation.lookup("show", "cinemaId", "cinemaId", "shows"),
                Aggregation.unwind("shows"),
                Aggregation.lookup("movie", "shows.movieId", "movieId", "movieDetails"),
                Aggregation.unwind("movieDetails"),
                Aggregation.match(Criteria.where("movieDetails.title").is(movieName)),
                Aggregation.lookup("seat", "shows.showId", "showId", "seatList"),
                Aggregation.replaceRoot("shows")
        );
        return mongoTemplate.aggregate(aggregation, "cinema", Show.class).getMappedResults();
    }

    @Override
    public List<Seat> getSeatsByShowId(String showId) {
        Query query = new Query(Criteria.where("showId").is(showId));
        return mongoTemplate.find(query, Seat.class);
    }
}
