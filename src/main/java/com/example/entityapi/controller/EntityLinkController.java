package com.example.entityapi.controller;

import com.example.entityapi.model.EntityLink;
import com.example.entityapi.service.EntityLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;

/**
 * Entity links/edges REST API with CRUDL endpoints.
 */
@RestController
class EntityLinkController
{
    @Autowired
    private EntityLinkService entityLinkService;

    private Logger logger = LoggerFactory.getLogger(EntityLinkController.class);

    @GetMapping("/entity/edges")
    public Collection<EntityLink> list()
    {
        logger.debug("List entity links");
        return entityLinkService.list();
    }

    @GetMapping("/entity/edges/edge/{id}")
    public EntityLink get(@PathVariable String id)
    {
        logger.debug("Get entity: " + id);
        return entityLinkService.get(id);
    }

    @GetMapping("/entity/edges/find")
    public Collection<EntityLink> find(@RequestBody Set<String> ids)
    {
        logger.debug("Find entity links by ids");
        return entityLinkService.getByIds(ids);
    }

    @GetMapping("/entity/edges/find/vertex/{entityId}")
    public Collection<EntityLink> find(@PathVariable String entityId)
    {
        logger.debug("Find entity links related entity: " + entityId);
        return entityLinkService.findEntityLinksRelatedToEntity(entityId);
    }

    @PostMapping("/entity/edges")
    public EntityLink create(@RequestBody @Valid EntityLink entityLink)
    {
        logger.debug("Create new entity link");
        return entityLinkService.createUpdate(entityLink);
    }

    @PutMapping("/entity/edges/edge/{id}")
    public EntityLink put(@PathVariable String id, @RequestBody @Valid EntityLink entityLink)
    {
        logger.debug("Update entity link: " + id);

        if (!entityLink.getId().equals(id))
        {
            entityLink.setId(id);
        }

        return entityLinkService.createUpdate(entityLink);
    }

    @DeleteMapping("/entity/edges/edge/{id}")
    public void delete(@PathVariable String id)
    {
        logger.debug("Delete entity link: " + id);
        entityLinkService.delete(id);
    }
}
