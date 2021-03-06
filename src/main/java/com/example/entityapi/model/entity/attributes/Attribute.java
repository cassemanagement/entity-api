package com.example.entityapi.model.entity.attributes;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Additional pieces of information for entities.
 */
@Data
public class Attribute
{
    boolean isRequired = false;

    private String label;

    @NotEmpty(message = "Please enter an attribute key")
    private String key;

    @NotEmpty(message = "Please enter an attribute value")
    private String value;

    private AttributeType type = AttributeType.STRING;
}
