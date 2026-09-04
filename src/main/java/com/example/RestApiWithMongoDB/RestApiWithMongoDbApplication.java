package com.example.RestApiWithMongoDB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class RestApiWithMongoDbApplication {

	static void main(String[] args) {

		SpringApplication.run(RestApiWithMongoDbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			StudentRepository repository, MongoTemplate mongoTemplate) {
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
					List.of("Computer Science", "Maths"),
					BigDecimal.TEN,
					LocalDateTime.now()

			);

			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));

			mongoTemplate.find(query, Student.class);

			repository.insert(student);
		};
	}

}
