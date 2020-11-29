package com.example.entityapi.model;

import com.microsoft.spring.data.gremlin.annotation.Edge;
import com.microsoft.spring.data.gremlin.annotation.EdgeFrom;
import com.microsoft.spring.data.gremlin.annotation.EdgeTo;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity edge model.
 */
@Edge
@Data
public class EntityLink
{
	@Id
	private String id;

	private String name;

	@EdgeFrom
	@NotNull(message = "Please provide start vertex")
	private Entity from;

	@EdgeTo
	@NotNull(message = "Please provide end vertex")
	private Entity to;

	private Map<String, Object> properties = new HashMap<>();
}
