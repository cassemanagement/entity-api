package com.example.entityapi.model.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Entity workflow details.
 */
@Data
public class Workflow
{
    @NotEmpty(message = "Workflow name required")
    private String name;

    @NotNull(message = "Workflow version required")
    private Integer version;

    @NotEmpty(message = "Workflow status required")
    private String status;
}
