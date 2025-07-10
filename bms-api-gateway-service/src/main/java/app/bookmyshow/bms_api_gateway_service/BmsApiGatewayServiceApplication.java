package app.bookmyshow.bms_api_gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BmsApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsApiGatewayServiceApplication.class, args);
	}

}
