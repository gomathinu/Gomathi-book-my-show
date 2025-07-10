package app.bookmyshow.bms_user_service.service.impl;

import app.bookmyshow.bms_user_service.model.User;
import app.bookmyshow.bms_user_service.repository.UserRepository;
import app.bookmyshow.bms_user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(String userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        return user.get();
    }
}
