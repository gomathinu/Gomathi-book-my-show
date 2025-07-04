package app.bookmyshow.bms_movie_service.repository;

import app.bookmyshow.bms_movie_service.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, Integer> {}