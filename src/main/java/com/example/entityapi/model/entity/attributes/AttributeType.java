package com.example.entityapi.model.entity.attributes;

import lombok.Getter;

/**
 * Variable types for attributes.
 */
public enum AttributeType
{
    STRING("String"),
    NUMBER("Number"),
    DATE("Date"),
    BOOLEAN("Boolean");

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
