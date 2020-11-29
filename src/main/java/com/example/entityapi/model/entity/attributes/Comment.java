package com.example.entityapi.model.entity.attributes;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

/**
 * Model for comments.
 */
@Data
public class Comment
{
	@Id
	@GeneratedValue // todo fix
	private String id;

	@NotBlank
	private String comment;

	@NotBlank
	private String createdBy;

	@NotBlank
	private String createdDate;
}
