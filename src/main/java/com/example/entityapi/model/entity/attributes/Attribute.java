package com.example.entityapi.model.entity.attributes;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Attribute
{
	@NotBlank
	private String key;

	@NotBlank
	private String value;

	@NotBlank
	private AttributeType type;

	boolean isRequired;

	boolean isImportant;
}
