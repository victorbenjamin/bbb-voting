package com.globo.bbb;

import com.globo.bbb.votes.VotePersistence;
import com.globo.bbb.votes.VoteService;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class BbbApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbbApplication.class, args);

	}

	@Bean
	VoteService voteService(@Autowired() VotePersistence persistence, @Value("${buffer.timeout}") long timespan) {
		return new VoteService(persistence, timespan);
	}

	public @Bean
	MongoClient mongoClient(@Value("${spring.data.mongodb.host}") String host, @Value("${spring.data.mongodb.port}") int port) {
		final MongoClientOptions options = MongoClientOptions
				.builder()
				.connectionsPerHost(1)
				.build();
		return new MongoClient(new ServerAddress(host, port), options);
	}

	public @Bean
	MongoTemplate mongoTemplate(
			@Value("${spring.data.mongodb.database}") String databaseName,
			@Value("${spring.data.mongodb.host}") String host,
			@Value("${spring.data.mongodb.port}") int port) {
		return new MongoTemplate(mongoClient(host, port), databaseName);
	}

}
