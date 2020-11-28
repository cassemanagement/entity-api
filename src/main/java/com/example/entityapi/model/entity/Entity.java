package com.example.entityapi.model.entity;

import com.example.entityapi.model.graph.CoreVertex;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Entity extends CoreVertex
{
	@NotBlank
	private String type;

	private String description;

	private List<Attribute> attributes;

	private List<Comment> comments;

	private String createdDate;
}
