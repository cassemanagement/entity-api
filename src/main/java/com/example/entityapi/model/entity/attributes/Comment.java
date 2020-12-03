package com.example.entityapi.model.entity.attributes;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

/**
 * Model for comments.
 */
@Data
public class Comment
{
    @Id
    private String id;

    @NotEmpty(message = "Please enter a comment")
    private String comment;

    private String createdBy;

    private String createdDate;
}
