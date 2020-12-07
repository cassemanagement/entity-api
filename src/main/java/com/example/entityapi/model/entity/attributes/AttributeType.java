package com.example.entityapi.model.entity.attributes;

import lombok.Getter;

/**
 * Variable types for attributes.
 */
public enum AttributeType
{
    ENTITY("ENTITY"),
    STRING("STRING"),
    NUMBER("NUMBER"),
    DATE("DATE"),
    BOOLEAN("BOOLEAN");

    @Getter
    private final String type;

    AttributeType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return getType();
    }
}
