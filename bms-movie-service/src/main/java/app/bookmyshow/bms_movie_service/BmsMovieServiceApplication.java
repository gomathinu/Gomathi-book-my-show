package app.bookmyshow.bms_movie_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BmsMovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmsMovieServiceApplication.class, args);
	}

}
