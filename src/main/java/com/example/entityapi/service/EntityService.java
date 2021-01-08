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

            if (entity.getCreatedDate() == null)
            {
                entity.setCreatedDate(new Date());
            }
            if (StringUtils.isBlank(entity.getCreatedBy()))
            {
                entity.setCreatedBy(userService.getUsername());
            }
        }
        else if (isCosmosDb)
        {
            // entity has to be deleted and re-created due to this gremlin
            // libraries incompatibility with Cosmos DB partition keys.
            repository.deleteById(entity.getId());
        }

        // ensure type is upper case
        entity.setType(entity.getType().toUpperCase());

        logger.debug("Create/update entity: " + entity.getId());
        return repository.save(entity);
    }

    @Override
    public void delete(String id)
    {
        logger.debug("Delete entity: " + id);
        repository.deleteById(id);
    }

    /**
     * Add a comment to the entity corresponding to the provided id.
     *
     * @param id      Id of entity to add a comment to.
     * @param comment Comment details to be added.
     * @return Amended entity.
     */
    public Entity comment(String id, Comment comment)
    {
        logger.debug("Add comment to entity: " + id);

        if (StringUtils.isBlank(comment.getId()))
        {
            comment.setId(UUID.randomUUID().toString());

            if (comment.getCreatedDate() == null)
            {
                comment.setCreatedDate(new Date());
            }
            if (StringUtils.isBlank(comment.getCreatedBy()))
            {
                comment.setCreatedBy(userService.getUsername());
            }
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

    public Entity progress(String id, String status)
    {
        logger.debug("Progress to entity (" + id + ") to: " + status);
        var entity = get(id);
        var workflow = entity.getWorkflow();
        workflow.setStatus(status);
        entity.setWorkflow(workflow);
        return createUpdate(entity);
    }

    /**
     * Find entities related to the provided type.
     *
     * @param type Entity type to search for.
     * @return List of entities of type.
     * @apiNote Gremlin currently does not support case insensitive search, so this assumes all
     * types are stored as upper case.
     */
    public Collection<Entity> findByType(String type)
    {
        return repository.findByType(type.toUpperCase());
    }
}
