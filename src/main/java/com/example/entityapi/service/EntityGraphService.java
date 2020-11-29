package com.example.entityapi.service;

import com.example.entityapi.model.EntityGraph;
import com.example.entityapi.repository.EntityGraphRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Create/Update/Delete Services for Entity graphs.
 */
@Service
public class EntityGraphService
{
	@Autowired
	private EntityGraphRepository repository;

	private Logger logger = LoggerFactory.getLogger(EntityGraphService.class);

	public EntityGraph createUpdate(EntityGraph entity)
	{
		logger.debug("Create/update entity graph: " + entity.getId());
		return repository.save(entity);
	}

	public void delete(String id)
	{
		logger.debug("Delete entity graph: " + id);
		repository.deleteById(id);
	}
}
