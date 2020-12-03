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

/**
 * Entity vertices REST API with CRUDL endpoints.
 */
@RestController
class EntityController
{
    @Autowired
    private EntityService entityService;

    private Logger logger = LoggerFactory.getLogger(EntityController.class);

    @GetMapping("/entity/vertices")
    public Collection<Entity> list()
    {
        logger.debug("List entities");
        return entityService.list();
    }

    @GetMapping("/entity/vertices/vertex/{id}")
    public Entity get(@PathVariable String id)
    {
        logger.debug("Get entity: " + id);
        return entityService.get(id);
    }

    @GetMapping("/entity/vertices/find")
    public Collection<Entity> find(@RequestBody Set<String> ids)
    {
        logger.debug("Find entities by ids");
        return entityService.getByIds(ids);
    }

    @PostMapping("/entity/vertices")
    public Entity create(@RequestBody @Valid Entity entity)
    {
        logger.debug("Create new entity");
        return entityService.createUpdate(entity);
    }

    @PutMapping("/entity/vertices/vertex/{id}")
    public Entity put(@PathVariable String id, @RequestBody @Valid Entity entity)
    {
        logger.debug("Update entity: " + id);

        if (!entity.getId().equals(id))
        {
            entity.setId(id);
        }

        return entityService.createUpdate(entity);
    }

    @DeleteMapping("/entity/vertices/vertex/{id}")
    public void delete(@PathVariable String id)
    {
        logger.debug("Delete entity: " + id);
        entityService.delete(id);
    }

    @PatchMapping("/entity/vertices/vertex/{id}/comment")
    public Entity comment(@PathVariable String id, @RequestBody @Valid Comment comment)
    {
        logger.debug("Add comment to entity: " + id);
        return entityService.comment(id,
                                     comment
        );
    }
}
