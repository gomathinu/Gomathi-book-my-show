package app.bookmyshow.bms_payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BmsPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsPaymentServiceApplication.class, args);
	}

}
