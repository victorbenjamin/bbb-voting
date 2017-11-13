package com.globo.bbb;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BbbApplication {

	public static void main(String[] args) {

		PublishSubject<Integer> subject = PublishSubject.create();
		subject.onNext(1);
		subject.subscribe(System.out::println);
		subject.onNext(2);
		subject.onNext(3);
		subject.onNext(4);
//		SpringApplication.run(BbbApplication.class, args);

	}
}
