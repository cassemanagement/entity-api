package com.example.entityapi.service;


import com.example.entityapi.model.Entity;
import com.example.entityapi.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service public class EntityService
{
	@Autowired
	private EntityRepository repository;

	public List<Entity> getAll()
	{
		var entities = new ArrayList<Entity>();
		var a = repository.findAll();

		a.forEach(entities::add);

		return entities;
	}

	public Entity create()
	{
		final String name = "bob";
		final String type = "person";
		final Entity example = new Entity(type + "_" + name, name, type);

		var saved = repository.save(example);

		System.out.println(repository.count());

		return saved;
	}

}
