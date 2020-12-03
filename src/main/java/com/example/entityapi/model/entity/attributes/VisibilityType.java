package com.example.entityapi.model.entity.attributes;

import lombok.Getter;

/**
 * Visibility type to determine when an attribute should be shown to users.
 */
public enum VisibilityType
{
    ALWAYS("Always"),
    MINIMISED("Minimised"),
    HIDDEN("Hidden");

    @Getter
    private final String type;

    VisibilityType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return getType();
    }
}
