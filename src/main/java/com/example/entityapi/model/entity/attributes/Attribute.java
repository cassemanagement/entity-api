package com.example.entityapi.model.entity.attributes;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Additional pieces of information for entities.
 */
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

	VisibilityType visibility = VisibilityType.MINIMISED;
}
