package app.bookmyshow.bms_booking_service.repository;

import app.bookmyshow.bms_booking_service.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, Integer> {
}
