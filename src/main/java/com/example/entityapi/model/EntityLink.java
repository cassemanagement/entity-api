package com.example.entityapi.model;

import com.example.entityapi.model.graph.core.CoreEdge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Entity edge model.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityLink extends CoreEdge<Entity>
{
	private Map<String, Object> properties = new HashMap<>();
}
