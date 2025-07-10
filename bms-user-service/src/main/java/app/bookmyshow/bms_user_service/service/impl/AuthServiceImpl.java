package app.bookmyshow.bms_user_service.service.impl;

import app.bookmyshow.bms_user_service.dto.request.RegisterRequest;
import app.bookmyshow.bms_user_service.dto.request.VerifyOtpRequest;
import app.bookmyshow.bms_user_service.dto.response.JwtResponse;
import app.bookmyshow.bms_user_service.model.User;
import app.bookmyshow.bms_user_service.repository.UserRepository;
import app.bookmyshow.bms_user_service.service.AuthService;
import app.bookmyshow.bms_user_service.util.JwtUtil;
import app.bookmyshow.bms_user_service.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final OtpUtil otpUtil;

    @Override
    public String register(RegisterRequest request) {
        if (userRepository.findByMobile(request.getMobile()).isPresent())
            return "User already exists";

        User user = new User();
        user.setMobile(request.getMobile());
        user.setEmail(request.getEmail());
        user.setOtpVerified(false);
        userRepository.save(user);
        return "User registered";
    }

    @Override
    public String sendOtp(String mobile) {
        User user = userRepository.findByMobile(mobile)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String otp = otpUtil.generateOtp();
        user.setOtp(otp);
        user.setOtpVerified(false);
        userRepository.save(user);

        // Simulate sending OTP
        System.out.println("OTP for " + mobile + ": " + otp);
        return "OTP sent";
    }

    @Override
    public JwtResponse verifyOtp(VerifyOtpRequest request) {
        User user = userRepository.findByMobile(request.getMobile())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!request.getOtp().equals(user.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        user.setOtpVerified(true);
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getMobile());
        return new JwtResponse(token);
    }
}
