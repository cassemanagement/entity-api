package com.example.entityapi.controller;

import com.example.entityapi.model.Entity;
import com.example.entityapi.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController public class EntityController
{
	@Autowired
	private EntityService entityService;

	@GetMapping("/entity") public List<Entity> list()
	{
		return entityService.getAll();
	}

	@GetMapping("/entity/create") public Entity create()
	{
		return entityService.create();
	}
}
