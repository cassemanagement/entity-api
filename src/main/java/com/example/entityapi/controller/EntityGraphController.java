package com.example.entityapi.controller;

import com.example.entityapi.model.EntityGraph;
import com.example.entityapi.service.EntityGraphService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

/**
 * Entity graph REST API with CRUD endpoints.
 */
@RestController
class EntityGraphController
{
	@Autowired
	private EntityGraphService entityGraphService;

	private Logger logger = LoggerFactory.getLogger(EntityGraphController.class);

	@GetMapping("/entity/graph/{id}")
	public EntityGraph get(@PathVariable String id)
	{
		logger.debug("Get entity graph: " + id);
		return entityGraphService.get(id);
	}

	@GetMapping("/entity/graph/find")
	public Collection<EntityGraph> find(@RequestBody Set<String> ids)
	{
		logger.debug("Find entity graphs by ids");
		return entityGraphService.getByIds(ids);
	}

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
