package app.bookmyshow.bms_user_service.util;

import app.bookmyshow.bms_user_service.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${bms.secret}")
    private String secret;

    public String generateToken(String mobile) {
        return Jwts.builder()
                .setSubject(mobile)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractMobile(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean isTokenValid(String token, User user) {
        return extractMobile(token).equals(user.getMobile());
    }
}
