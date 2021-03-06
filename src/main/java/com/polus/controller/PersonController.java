package com.polus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.polus.entity.Person;
import com.polus.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;
	
	@GetMapping("/asynchCall")
	public void asynchCall() throws InterruptedException, ExecutionException {
		LOGGER.info("Asynch call start...");
		
		CompletableFuture<Person> person1 = personService.findPersonByName("Mirko");
		CompletableFuture<Person> person2 = personService.findPersonByName("Tijana");
		CompletableFuture<Person> person3 = personService.findPersonByName("Branko");
		
		CompletableFuture.allOf(person1, person2, person3).join();
		LOGGER.info("Asynch call end...");
		LOGGER.info("-----> " + person1.get());
		LOGGER.info("-----> " + person2.get());
		LOGGER.info("-----> " + person3.get());
	}
	
	@GetMapping("/allPerons")
	public List<Person> getAllPersons(){
		return personService.getAllPersons();
	}
	
	@PostMapping("/insertPersons")
	public ResponseEntity insertPersons(@RequestBody List<Person> persons) {
		LOGGER.info("Insert Person start...");
		try {
			
				personService.insertPerson(persons);
			
			LOGGER.info("Insert Person end...");
			return ResponseEntity.ok("Persons added to the database");
		}catch (Exception e) {
			return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
