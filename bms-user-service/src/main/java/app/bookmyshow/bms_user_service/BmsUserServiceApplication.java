package app.bookmyshow.bms_user_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "app.bookmyshow")
@EnableDiscoveryClient
public class BmsUserServiceApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(BmsUserServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BmsUserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("BmsUserServiceApplication: started running service........");
		log.debug("BmsUserServiceApplication: started running service........");
		System.out.println("BmsUserServiceApplication: started running service........");
	}

}
