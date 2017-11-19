package com.globo.bbb;

import com.globo.bbb.com.globo.bbb.votes.VotePersistence;
import com.globo.bbb.com.globo.bbb.votes.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class BbbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbbApplication.class, args);

	}

	@Bean
	VoteService voteService(@Autowired() VotePersistence persistence, @Value("${buffer.timeout}") long timespan) {
		return new VoteService(persistence, timespan);
	}

//	@Bean
//	CommandLineRunner init(VotePersistence persistence) {
//
//		return args -> {
//			final VotesHour votes = new VotesHour(5, 9);
//			persistence.persist(votes);
//			persistence.all().stream().map(VotesHour::getHour).forEach(System.out::println);
//
//		};
//
//	}

}
