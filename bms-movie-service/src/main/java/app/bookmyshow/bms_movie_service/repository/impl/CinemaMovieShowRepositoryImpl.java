package app.bookmyshow.bms_movie_service.repository.impl;

import app.bookmyshow.bms_movie_service.model.Movie;
import app.bookmyshow.bms_movie_service.model.Seat;
import app.bookmyshow.bms_movie_service.model.Show;
import app.bookmyshow.bms_movie_service.repository.CinemaMovieShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CinemaMovieShowRepositoryImpl implements CinemaMovieShowRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Movie> getMoviesByCityName(String cityName) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.lookup("cinema", "cinemaId", "id", "cinema"),
                Aggregation.unwind("cinema"),
                Aggregation.lookup("city", "cinema.city", "id", "city"),
                Aggregation.unwind("city"),
                Aggregation.match(Criteria.where("city.name").is(cityName)),
                Aggregation.lookup("movie", "movieId", "id", "movie"),
                Aggregation.unwind("movie"),
                Aggregation.group("movie.id").first("movie").as("movie")
        );

        return mongoTemplate.aggregate(agg, "movie", Movie.class).getMappedResults();
    }

    @Override
    public List<Movie> getMoviesByCinemaName(String cinemaName) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("cinemaName").is(cinemaName)),
                Aggregation.lookup("show", "id", "cinemaId", "shows"),
                Aggregation.unwind("shows"),
                Aggregation.lookup("movie", "shows.movieId", "id", "movieDetails"),
                Aggregation.unwind("movieDetails"),
                Aggregation.group("movieDetails.id").first("movieDetails").as("movie"),
                Aggregation.replaceRoot("movie")
        );
        return mongoTemplate.aggregate(aggregation, "cinema", Movie.class).getMappedResults();
    }

    @Override
    public List<Show> getShowsByMovieAndCinema(String movieName, String cinemaName) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("cinemaName").is(cinemaName)),
                Aggregation.lookup("show", "id", "cinemaId", "shows"),
                Aggregation.unwind("shows"),
                Aggregation.lookup("movie", "shows.movieId", "id", "movieDetails"),
                Aggregation.unwind("movieDetails"),
                Aggregation.match(Criteria.where("movieDetails.name").is(movieName)),
                Aggregation.replaceRoot("shows")
        );
        return mongoTemplate.aggregate(aggregation, "cinema", Show.class).getMappedResults();
    }

    @Override
    public List<Seat> getSeatsByShowId(Integer showId) {
        Query query = new Query(Criteria.where("showId").is(showId));
        return mongoTemplate.find(query, Seat.class);
    }
}
