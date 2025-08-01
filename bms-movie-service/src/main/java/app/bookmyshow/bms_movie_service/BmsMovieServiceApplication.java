package app.bookmyshow.bms_movie_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BmsMovieServiceApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(BmsMovieServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BmsMovieServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("BmsMovieServiceApplication run() is called");
		log.debug("BmsMovieServiceApplication run() is called");
		System.out.println("BmsMovieServiceApplication run() is called");
	}

}
