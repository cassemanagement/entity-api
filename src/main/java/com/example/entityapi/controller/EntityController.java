package com.example.entityapi.controller;

import com.example.entityapi.model.Entity;
import com.example.entityapi.model.entity.attributes.Comment;
import com.example.entityapi.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

@RestController
class EntityController
{
	@Autowired
	private EntityService entityService;

	private Logger logger = LoggerFactory.getLogger(EntityController.class);

	@GetMapping("/entities")
	public Collection<Entity> list()
	{
		logger.debug("List entities");
		return entityService.list();
	}

	@GetMapping("/entities/entity/{id}")
	public Entity get(@PathVariable String id)
	{
		logger.debug("Get entity: " + id);
		return entityService.get(id);
	}

	@GetMapping("/entities/find")
	public Collection<Entity> find(@RequestBody Set<String> ids)
	{
		logger.debug("Find entities");
		return entityService.getByIds(ids);
	}

	@PostMapping("/entities/entity")
	public Entity create(@RequestBody @Valid Entity entity)
	{
		logger.debug("Create new entity");
		return entityService.createUpdate(entity);
	}

	@PutMapping("/entities/entity/{id}")
	public Entity put(@PathVariable String id, @RequestBody @Valid Entity entity)
	{
		logger.debug("Update entity: " + id);

		if (!entity.getId().equals(id))
		{
			entity.setId(id);
		}

		return entityService.createUpdate(entity);
	}

	@DeleteMapping("/entities/entity/{id}")
	public void delete(@PathVariable String id)
	{
		logger.debug("Delete entity: " + id);
		entityService.delete(id);
	}

	@PatchMapping("/entities/entity/{id}/comment")
	public Entity comment(@PathVariable String id, @RequestBody @Valid Comment comment)
	{
		logger.debug("Add comment to entity: " + id);
		return entityService.comment(id, comment);
	}
}
