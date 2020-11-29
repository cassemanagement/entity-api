package com.example.entityapi.model.graph.core;

import com.microsoft.spring.data.gremlin.annotation.EdgeSet;
import com.microsoft.spring.data.gremlin.annotation.Graph;
import com.microsoft.spring.data.gremlin.annotation.VertexSet;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

/**
 * Core model for a gremlin graph.
 */
@Graph
@Data
public class CoreGraph<V extends CoreVertex, E extends CoreEdge<V>>
{
	@Id
	private String id;

	@EdgeSet
	private Set<E> edges;

	@VertexSet
	private Set<V> vertexes;

	public CoreGraph()
	{
		this.edges = new HashSet<>();
		this.vertexes = new HashSet<>();
	}

}
