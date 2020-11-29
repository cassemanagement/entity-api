package com.example.entityapi.service;

import com.example.entityapi.model.EntityGraph;
import com.example.entityapi.repository.EntityGraphRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * CRUDL Services for Entity graphs.
 */
@Service
public class EntityGraphService implements CrudRestService<EntityGraph>
{
	@Autowired
	private EntityGraphRepository repository;

	private Logger logger = LoggerFactory.getLogger(EntityGraphService.class);

	@Override
	public EntityGraph get(String id)
	{
		logger.debug("Get entity graph: " + id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public Collection<EntityGraph> getByIds(Set ids)
	{
		logger.debug("Find entity graphs");
		var entities = new HashSet<EntityGraph>();
		Iterable<EntityGraph> results = repository.findAllById(ids);
		results.forEach(entities::add);
		return entities;
	}

	@Override
	public EntityGraph createUpdate(EntityGraph entity)
	{
		logger.debug("Create/update entity graph: " + entity.getId());
		return repository.save(entity);
	}

	@Override
	public void delete(String id)
	{
		logger.debug("Delete entity graph: " + id);
		repository.deleteById(id);
	}
}
