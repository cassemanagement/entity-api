package com.example.entityapi.model.entity;

import com.example.entityapi.model.graph.CoreEdge;
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
