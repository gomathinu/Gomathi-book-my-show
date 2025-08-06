package app.bookmyshow.bms_messaging_service.service.impl;

import app.bookmyshow.bms_messaging_service.kafka.BookingConfirmedUser;
import app.bookmyshow.bms_messaging_service.model.User;
import app.bookmyshow.bms_messaging_service.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MessagingServiceImpl implements MessagingService {

    private static final Logger log = LoggerFactory.getLogger(MessagingServiceImpl.class);

    @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    private final JavaMailSender mailSender;

    @Override
    public void sendBookingSuccessMessage(String bookingId, String userId, String token) {
        // REST Call to User Service to fetch email and phone
        String userServiceUrl = "http://bms-api-gateway-service:8080/bms/user/userDetails/getUserDetails" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<User> response = restTemplate.exchange(
                userServiceUrl, HttpMethod.GET, entity, User.class
        );
        User user = response.getBody();
        if (user == null) return;
        String subject = "Your Booking is Confirmed!";
        String message = String.format("Hi %s,\n\nYour booking (booking Id: %d) for movie is confirmed.\nThank you!",
               userId, bookingId);
        //TODO: could get seat information etc from movie service and include it here
        sendEmail(user.getEmail(), subject, message);
        sendSms(user.getMobile(), message);
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            mailSender.send(msg);
            log.info("Email sent for booking confirmation");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void sendSms(String phone, String content) {
        log.info("Sending SMS to {}: {}", phone, content);
    }
}
