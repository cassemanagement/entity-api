package com.example.entityapi.service;


import com.example.entityapi.model.Entity;
import com.example.entityapi.model.entity.attributes.Comment;
import com.example.entityapi.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class EntityService implements IRestService<Entity>
{
	@Autowired
	private EntityRepository repository;


	@Override
	public Collection<Entity> list()
	{
		var entities = new HashSet<Entity>();
		repository.findAll().forEach(entities::add);
		return entities;
	}

	@Override
	public Entity get(String id)
	{
		return repository.findById(id).orElse(null);
	}

	@Override
	public Collection<Entity> getByIds(Set ids)
	{
		var entities = new HashSet<Entity>();
		Iterable<Entity> results = repository.findAllById(ids);
		results.forEach(entities::add);
		return entities;
	}

	@Override
	public Entity createUpdate(Entity entity)
	{
		return repository.save(entity);
	}

	@Override
	public void delete(String id)
	{
		repository.deleteById(id);
	}

	public Entity comment(String id, Comment comment)
	{
		var entity = get(id);
		var comments = entity.getComments();
		comments.add(comment);
		entity.setComments(comments);
		return createUpdate(entity);
	}
}
