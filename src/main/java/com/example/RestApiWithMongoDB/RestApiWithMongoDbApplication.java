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

            // usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);

            repository.findStudentByEmail(email)
                    .ifPresentOrElse(s -> {
						System.out.println(s + " already exists");
                    }, () -> {
                        System.out.println("Inserting student " + student);
                        repository.insert(student);
                    });
        };

    }

    private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate) {

		Query query = new Query();
		query.addCriteria(Criteria.where("email").

				is(email));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1) {
			throw new IllegalStateException("found many students with email " + email);
		}
		if (students.isEmpty()) {
			System.out.println("Inserting student " + student);
			repository.insert(student);
		} else {
			System.out.println(student + " Already exists");
		}

	}
}
