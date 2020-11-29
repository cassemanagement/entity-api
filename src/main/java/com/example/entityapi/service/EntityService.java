package com.example.entityapi.service;

import com.example.entityapi.model.Entity;
import com.example.entityapi.model.entity.attributes.Comment;
import com.example.entityapi.repository.EntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * CRUDL Services for Entity vertices.
 */
@Service
public class EntityService implements CrudlRestService<Entity>
{
	@Autowired
	private EntityRepository repository;

	private Logger logger = LoggerFactory.getLogger(EntityService.class);

	@Override
	public Collection<Entity> list()
	{
		logger.debug("List entities");
		var entities = new HashSet<Entity>();
		repository.findAll().forEach(entities::add);
		return entities;
	}

	@Override
	public Entity get(String id)
	{
		logger.debug("Get entity: " + id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public Collection<Entity> getByIds(Set ids)
	{
		logger.debug("Find entities");
		var entities = new HashSet<Entity>();
		Iterable<Entity> results = repository.findAllById(ids);
		results.forEach(entities::add);
		return entities;
	}

	@Override
	public Entity createUpdate(Entity entity)
	{
		logger.debug("Create/update entity: " + entity.getId());
		return repository.save(entity);
	}

	@Override
	public void delete(String id)
	{
		logger.debug("Delete entity: " + id);
		repository.deleteById(id);
	}

	public Entity comment(String id, Comment comment)
	{
		logger.debug("Add comment to entity: " + id);
		var entity = get(id);
		var comments = entity.getComments();
		comments.add(comment);
		entity.setComments(comments);
		return createUpdate(entity);
	}
}
