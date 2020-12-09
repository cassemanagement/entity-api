package com.example.entityapi.model.entity.workflow;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class WorkflowState
{
    @NotEmpty(message = "Workflow Id not provided")
    private String workflowId;

    @NotEmpty(message = "Workflow Step Id not provided")
    private String currentStepId;
}
