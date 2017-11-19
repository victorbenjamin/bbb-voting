package com.globo.bbb;

import com.globo.bbb.votes.VotePersistence;
import com.globo.bbb.votes.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BbbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbbApplication.class, args);

	}

	@Bean
	VoteService voteService(@Autowired() VotePersistence persistence, @Value("${buffer.timeout}") long timespan) {
		return new VoteService(persistence, timespan);
	}

}
