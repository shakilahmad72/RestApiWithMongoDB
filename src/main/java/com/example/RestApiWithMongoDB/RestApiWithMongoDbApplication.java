package com.example.RestApiWithMongoDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class RestApiWithMongoDbApplication {

	static void main(String[] args) {

		SpringApplication.run(RestApiWithMongoDbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository) {
		return args -> {
			Address address = new Address(
					"India",
					"Bokaro",
					"123456"
			);

			Student student = new Student(
					"Shakil",
					"Ahmad",
					"example@gmail.com",
					Gender.MALE,
					address,
					List.of("Computer Science"),
					BigDecimal.TEN,
					ZonedDateTime.now()

			);


		};
	}

}
