package com.example.entityapi.model;

import com.example.entityapi.model.graph.core.CoreEdge;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityLink extends CoreEdge<Entity>
{
	private Map<String, Object> properties = new HashMap<>();
}
