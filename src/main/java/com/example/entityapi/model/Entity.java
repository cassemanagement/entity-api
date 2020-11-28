package com.example.entityapi.model;

import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Vertex @Data @AllArgsConstructor @NoArgsConstructor
public class Entity
{
	@Id
	private String id;

	private String name;

	private String type;
}
