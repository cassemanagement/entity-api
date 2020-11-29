package com.example.entityapi.model;

import com.example.entityapi.model.entity.attributes.Attribute;
import com.example.entityapi.model.entity.attributes.Comment;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Entity vertex model.
 */
@Vertex
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity
{
	@Id
	private String id;

	@NotEmpty(message = "Please enter a name")
	private String name;

	@NotEmpty(message = "Please enter a type")
	private String type;

	private String description;

	@Valid
	private List<Attribute> attributes;

	@Valid
	private List<Comment> comments;

	private String createdDate;
}
