package com.example.entityapi.service;

import com.example.entityapi.model.Entity;
import com.example.entityapi.model.entity.attributes.Comment;
import com.example.entityapi.repository.EntityRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * CRUDL Services for Entity vertices.
 */
@Service
public class EntityService implements CrudlRestService<Entity>
{
    @Autowired
    private EntityRepository repository;

    @Autowired
    private UserService userService;

    @Value("#{new Boolean('${database.isCosmosDb}')}")
    private Boolean isCosmosDb;

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
    public Collection<Entity> getByIds(Collection ids)
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
        if (StringUtils.isBlank(entity.getId()))
        {
            entity.setId(UUID.randomUUID().toString());

            if(entity.getCreatedDate() == null){
                entity.setCreatedDate(new Date());
            }
            if(entity.getCreatedBy() == null){
                entity.setCreatedBy(userService.getUsername());
            }
        }
        else if (isCosmosDb)
        {
            // entity has to be deleted and re-created due to this gremlin
            // libraries incompatibility with Cosmos DB partition keys.
            repository.deleteById(entity.getId());
        }

        logger.debug("Create/update entity: " + entity.getId());

        if (entity.getComments() != null)
        {
            for (var comment : entity.getComments())
            {
                if (StringUtils.isBlank(comment.getId()))
                {
                    comment.setId(UUID.randomUUID().toString());

                    if(comment.getCreatedDate() == null){
                        entity.setCreatedDate(new Date());
                    }
                    if(comment.getCreatedBy() == null){
                        entity.setCreatedBy(userService.getUsername());
                    }
                }
            }
        }

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

        if (StringUtils.isBlank(comment.getId()))
        {
            comment.setId(UUID.randomUUID().toString());
        }

        var entity = get(id);
        var comments = entity.getComments();
        if (comments == null)
        {
            comments = new ArrayList<>();
        }
        comments.add(comment);
        entity.setComments(comments);

        return createUpdate(entity);
    }
}
