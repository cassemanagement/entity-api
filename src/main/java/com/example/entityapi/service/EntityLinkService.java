package com.example.entityapi.service;

import com.example.entityapi.model.EntityLink;
import com.example.entityapi.repository.EntityLinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * CRUDL Services for Entity edges.
 */
@Service
public class EntityLinkService implements CrudlRestService<EntityLink>
{
	@Autowired
	private EntityLinkRepository repository;

	private Logger logger = LoggerFactory.getLogger(EntityLinkService.class);

	@Override
	public Collection<EntityLink> list()
	{
		logger.debug("List entity links");
		var entities = new HashSet<EntityLink>();
		repository.findAll().forEach(entities::add);
		return entities;
	}

	@Override
	public EntityLink get(String id)
	{
		logger.debug("Get entity link: " + id);
		return repository.findById(id).orElse(null);
	}

	@Override
	public Collection<EntityLink> getByIds(Set ids)
	{
		logger.debug("Find entity links");
		var entities = new HashSet<EntityLink>();
		Iterable<EntityLink> results = repository.findAllById(ids);
		results.forEach(entities::add);
		return entities;
	}

	@Override
	public EntityLink createUpdate(EntityLink entityLink)
	{
		logger.debug("Create/update entity link: " + entityLink.getId());
		return repository.save(entityLink);
	}

	@Override
	public void delete(String id)
	{
		logger.debug("Delete entity link: " + id);
		repository.deleteById(id);
	}
}
