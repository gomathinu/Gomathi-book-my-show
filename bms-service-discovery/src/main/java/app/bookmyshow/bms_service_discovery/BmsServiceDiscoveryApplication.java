package app.bookmyshow.bms_service_discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BmsServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsServiceDiscoveryApplication.class, args);
	}

}
