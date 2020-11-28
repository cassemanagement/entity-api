package com.example.entityapi.model.entity;

import com.example.entityapi.model.graph.CoreGraph;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityGraph extends CoreGraph<Entity, EntityLink>
{
	public EntityGraph()
	{
		super();
	}
}
