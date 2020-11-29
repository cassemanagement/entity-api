package com.example.entityapi.repository;

import com.example.entityapi.model.Entity;
import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for entity vertices.
 */
@Repository
public interface EntityRepository extends GremlinRepository<Entity, String>
{
}