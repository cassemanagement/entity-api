package com.example.entityapi.model;

import com.microsoft.spring.data.gremlin.annotation.EdgeSet;
import com.microsoft.spring.data.gremlin.annotation.Graph;
import com.microsoft.spring.data.gremlin.annotation.VertexSet;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

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
	private List<EntityLink> edges;

	@VertexSet
	private List<Entity> vertices;

	public EntityGraph()
	{
		this.edges = new ArrayList<>();
		this.vertices = new ArrayList<>();
	}
}
