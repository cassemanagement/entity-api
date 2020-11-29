package com.example.entityapi.repository;

import com.example.entityapi.model.EntityGraph;
import com.microsoft.spring.data.gremlin.repository.GremlinRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for entity graphs.
 */
@Repository
public interface EntityGraphRepository extends GremlinRepository<EntityGraph, String>
{
}