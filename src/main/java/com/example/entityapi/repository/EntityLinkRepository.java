package com.example.entityapi.repository;

import com.example.entityapi.model.EntityLink;
import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for entity edges.
 */
@Repository
public interface EntityLinkRepository extends GremlinRepository<EntityLink, String>
{
}