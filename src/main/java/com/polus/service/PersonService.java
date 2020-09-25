package com.polus.service;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.polus.dao.PersonRepository;
import com.polus.entity.Person;

@Service
public class PersonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	PersonRepository personRepository;
	
	@Async("threadPoolExecutor1")
	public CompletableFuture<Person> findPersonByName(String name) throws InterruptedException{
		LOGGER.info("Finding perons by name: " + name);
		
		Person person = personRepository.findByName(name);
		
		Thread.sleep(1000);
		
		return CompletableFuture.completedFuture(person);
	}
	
	public List<Person> getAllPersons(){
		return (List<Person>) personRepository.findAll();
	}
	
	@Async("threadPoolExecutor1")
	public void insertPerson(Person person) {
		CompletableFuture.completedFuture(personRepository.save(person));
		
	}
}
