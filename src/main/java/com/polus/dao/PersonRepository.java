package com.polus.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.polus.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Person findByName(String name);
	
	
}
