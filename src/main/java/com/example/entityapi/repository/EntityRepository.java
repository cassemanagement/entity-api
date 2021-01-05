package com.example.entityapi.repository;

import com.example.entityapi.model.Entity;
import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repository for entity vertices.
 */
@Repository
public interface EntityRepository extends GremlinRepository<Entity, String>
{
    // NB Gremlin currently doesn't support 'IgnoreCase'
    Collection<Entity> findByType(String type);
}