package com.example.entityapi.model.graph.core;

import com.microsoft.spring.data.gremlin.annotation.EdgeSet;
import com.microsoft.spring.data.gremlin.annotation.Graph;
import com.microsoft.spring.data.gremlin.annotation.VertexSet;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Graph
@Data
public class CoreGraph<V extends CoreVertex, E extends CoreEdge<V>>
{
	@Id
	private String id;

	@EdgeSet
	private List<E> edges;

	@VertexSet
	private List<V> vertexes;

	public CoreGraph()
	{
		this.edges = new ArrayList<>();
		this.vertexes = new ArrayList<>();
	}

}
