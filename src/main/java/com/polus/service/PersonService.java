package com.polus.service;


import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polus.entity.Person;

@Service
public class PersonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	
	String baza;
	
	private RestTemplate restTemplate;

	public PersonService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Async("threadPoolExecutor1")
	public CompletableFuture<Person> findPersonByName(String name) throws InterruptedException{
		LOGGER.info("Finding perons by name: " + name);
		
		Person person = restTemplate.getForObject(baza, Person.class);
		
		Thread.sleep(1000);
		
		return CompletableFuture.completedFuture(person);
	}
}
