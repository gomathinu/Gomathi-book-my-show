package app.bookmyshow.bms_movie_service.repository;

import app.bookmyshow.bms_movie_service.model.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SeatRepository extends MongoRepository<Seat, String> {
    Optional<Seat> findBySeatId(String seatId);
}