package com.example.entityapi.model.graph.core;

import com.microsoft.spring.data.gremlin.annotation.Edge;
import com.microsoft.spring.data.gremlin.annotation.EdgeFrom;
import com.microsoft.spring.data.gremlin.annotation.EdgeTo;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * Core model for a gremlin graph edge.
 */
@Edge
@Data
public class CoreEdge<V extends CoreVertex>
{
	@Id
	private String id;

	private String name;

	@EdgeFrom
	@NotNull
	private V from;

	@EdgeTo
	@NotNull
	private V to;
}
