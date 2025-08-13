package app.bookmyshow.bms_user_service.service;

import app.bookmyshow.bms_user_service.dto.request.RegisterRequest;
import app.bookmyshow.bms_user_service.dto.request.VerifyOtpRequest;
import app.bookmyshow.bms_user_service.dto.response.JwtResponse;
import app.bookmyshow.bms_user_service.dto.response.JwtResponseWithUserDetails;

public interface AuthService {

    public String register(RegisterRequest request);

    public String sendOtp(String mobile);

    public JwtResponseWithUserDetails verifyOtp(VerifyOtpRequest request);

    public JwtResponse getToken(VerifyOtpRequest request);
}