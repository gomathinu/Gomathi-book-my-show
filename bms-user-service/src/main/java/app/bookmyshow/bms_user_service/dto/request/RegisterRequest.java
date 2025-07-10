package app.bookmyshow.bms_user_service.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String mobile;
    private String email;
}
