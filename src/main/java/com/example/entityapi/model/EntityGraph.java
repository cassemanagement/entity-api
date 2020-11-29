package com.example.entityapi.model;

import com.microsoft.spring.data.gremlin.annotation.EdgeSet;
import com.microsoft.spring.data.gremlin.annotation.Graph;
import com.microsoft.spring.data.gremlin.annotation.VertexSet;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity graph model.
 */
@Graph
@Data
public class EntityGraph
{

	@Id
	private String id;

	@EdgeSet
	private Set<EntityLink> edges;

	@VertexSet
	private Set<Entity> vertexes;

	public EntityGraph()
	{
		this.edges = new HashSet<>();
		this.vertexes = new HashSet<>();
	}
}
