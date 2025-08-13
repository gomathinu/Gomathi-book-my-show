package app.bookmyshow.bms_user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseWithUserDetails {
    private String jwtToken;
    private String userId;
}
