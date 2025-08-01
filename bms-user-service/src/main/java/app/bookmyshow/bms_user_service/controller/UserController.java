package app.bookmyshow.bms_user_service.controller;

import app.bookmyshow.bms_user_service.dto.request.RegisterRequest;
import app.bookmyshow.bms_user_service.dto.request.SendOtpRequest;
import app.bookmyshow.bms_user_service.dto.request.VerifyOtpRequest;
import app.bookmyshow.bms_user_service.dto.response.JwtResponse;
import app.bookmyshow.bms_user_service.model.User;
import app.bookmyshow.bms_user_service.service.AuthService;
import app.bookmyshow.bms_user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/userDetails")
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final AuthService authService;
    @Autowired
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        log.info("UserController: register() is called");
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SendOtpRequest request) {
        log.info("UserController: login() is called");
        return ResponseEntity.ok(authService.sendOtp(request.getMobile()));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<JwtResponse> verifyOtp(@RequestBody VerifyOtpRequest request) {
        log.info("UserController: verify-otp() is called");
        return ResponseEntity.ok(authService.verifyOtp(request));
    }

    //This is for admin role - to get token on mobile and otp
    @PostMapping("/getToken")
    public ResponseEntity<JwtResponse> getToken(@RequestBody VerifyOtpRequest request) {
        System.out.println("UserController: getToken() is called");
        log.info("UserController: getToken() is called");
        return ResponseEntity.ok(authService.verifyOtp(request));
    }

    //below api is to get user details for messaging service after booking confirmation
    @GetMapping("/getUserDetails/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        log.info("UserController: getUserById() is called");
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}
