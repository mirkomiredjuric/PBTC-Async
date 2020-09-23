package com.polus.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polus.entity.Person;
import com.polus.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;
	
	//poslati kroz body pozive za imena?
	@GetMapping("/asynchCall")
	public void asynchCall() throws InterruptedException {
		LOGGER.info("Asynch call start...");
		
		CompletableFuture<Person> person1 = personService.findPersonByName("Mirko");
		CompletableFuture<Person> person2 = personService.findPersonByName("Tijana");
		CompletableFuture<Person> person3 = personService.findPersonByName("Branko");
		
		CompletableFuture.allOf(person1, person2, person3).join();
		
	}
}
