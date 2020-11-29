package com.example.entityapi.model.graph.core;

import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Vertex
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoreVertex
{
	@Id
	private String id;

	@NotBlank
	private String name;
}
