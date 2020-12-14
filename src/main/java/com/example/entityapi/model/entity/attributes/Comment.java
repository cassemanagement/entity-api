package com.example.entityapi.model.entity.attributes;

import lombok.Data;
import org.apache.tinkerpop.shaded.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdDate;
}
