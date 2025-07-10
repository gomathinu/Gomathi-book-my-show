package app.bookmyshow.bms_user_service.repository;

import app.bookmyshow.bms_user_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByMobile(String mobile);
    Optional<User> findByUserId(String userId);
}