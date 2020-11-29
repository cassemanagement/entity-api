package com.example.entityapi.model.entity.attributes;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

/**
 * Model for comments.
 */
@Data
public class Comment
{
	@Id
	@GeneratedValue // todo fix
	private String id;

	@NotEmpty(message = "Please enter a comment")
	private String comment;

	private String createdBy;

	private String createdDate;
}
