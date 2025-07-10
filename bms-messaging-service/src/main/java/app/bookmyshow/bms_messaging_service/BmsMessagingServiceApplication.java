package app.bookmyshow.bms_messaging_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BmsMessagingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsMessagingServiceApplication.class, args);
	}

}
