package com.example.entityapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EntityController
{

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/entity")
	public Entity entity(@RequestParam(value = "name", defaultValue = "World") String name) {
		var entity = new Entity();
		entity.setName(name);
		return entity;
	}
}
