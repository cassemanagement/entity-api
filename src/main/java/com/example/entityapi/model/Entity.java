package com.example.entityapi.model;

import com.example.entityapi.model.entity.attributes.Attribute;
import com.example.entityapi.model.entity.attributes.Comment;
import com.example.entityapi.model.graph.core.CoreVertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity extends CoreVertex
{
	@NotBlank
	private String type;

	private String description;

	private List<Attribute> attributes;

	private List<Comment> comments;

	private String createdDate;
}
