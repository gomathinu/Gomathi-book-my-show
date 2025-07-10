package app.bookmyshow.bms_user_service.service;

import app.bookmyshow.bms_user_service.dto.request.RegisterRequest;
import app.bookmyshow.bms_user_service.dto.request.VerifyOtpRequest;
import app.bookmyshow.bms_user_service.dto.response.JwtResponse;

public interface AuthService {

    public String register(RegisterRequest request);

    public String sendOtp(String mobile);

    public JwtResponse verifyOtp(VerifyOtpRequest request);
}