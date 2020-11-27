package com.example.entityapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

@RestController
public class EntityController
{
	private EntityService entityService = new EntityService();

	@GetMapping("/entity")
	public Entity entity(@RequestParam(value = "name", defaultValue = "World") String name) {

		try
		{
			entityService.getEntities();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (URISyntaxException e)
		{
			e.printStackTrace();
		}

		var entity = new Entity();
		entity.setName(name);

		return entity;
	}
}
