package com.example.entityapi.controller;

import com.example.entityapi.model.EntityGraph;
import com.example.entityapi.service.EntityGraphService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Entity graph REST API with CRUD endpoints.
 */
@RestController
class EntityGraphController
{
	@Autowired
	private EntityGraphService entityGraphService;

	private Logger logger = LoggerFactory.getLogger(EntityGraphController.class);

	@PostMapping("/entity/graph")
	public EntityGraph create(@RequestBody @Valid EntityGraph entityGraph)
	{
		logger.debug("Create new entity graph");
		return entityGraphService.createUpdate(entityGraph);
	}

	@PutMapping("/entity/graph/{id}")
	public EntityGraph put(@PathVariable String id, @RequestBody @Valid EntityGraph entityGraph)
	{
		logger.debug("Update entity graph: " + id);

		if (!entityGraph.getId().equals(id))
		{
			entityGraph.setId(id);
		}

		return entityGraphService.createUpdate(entityGraph);
	}

	@DeleteMapping("/entity/graph/{id}")
	public void delete(@PathVariable String id)
	{
		logger.debug("Delete entity graph: " + id);
		entityGraphService.delete(id);
	}
}
