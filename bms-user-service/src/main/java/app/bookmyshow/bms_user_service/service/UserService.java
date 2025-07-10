package app.bookmyshow.bms_user_service.service;

import app.bookmyshow.bms_user_service.model.User;

import java.util.Optional;

public interface UserService {
    User getUserById(String id);
}