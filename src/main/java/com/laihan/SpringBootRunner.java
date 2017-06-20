package com.laihan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.laihan.configuration.EmbeddedBroker;

@SpringBootApplication
@Import({
	EmbeddedBroker.class
})
public class SpringBootRunner {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRunner.class, args);
	}
}
