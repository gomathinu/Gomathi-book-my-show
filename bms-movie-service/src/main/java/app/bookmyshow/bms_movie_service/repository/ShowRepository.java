package app.bookmyshow.bms_movie_service.repository;

import app.bookmyshow.bms_movie_service.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShowRepository extends MongoRepository<Show, Integer> {
    Optional<Show> findByShowId(Integer showId);
}