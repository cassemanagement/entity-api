package com.example.entityapi.model.entity;

import com.microsoft.spring.data.gremlin.annotation.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
public class Comment
{
	@Id
	@GeneratedValue
	private String id;

	@NotBlank
	private String comment;

	@NotBlank
	private String createdBy;

	@NotBlank
	private String createdDate;
}
