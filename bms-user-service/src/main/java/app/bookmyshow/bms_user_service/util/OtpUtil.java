package app.bookmyshow.bms_user_service.util;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class OtpUtil {
    public String generateOtp() {
        return String.valueOf(new Random());
    }
}
