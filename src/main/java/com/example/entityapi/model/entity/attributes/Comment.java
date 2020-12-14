package com.example.entityapi.model.entity.attributes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
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

    // Date is used as only that is supported by Gremlin.
    // JsonFormat is com.fasterxml.jackson (not org.apache.tinkerpop.shaded.jackson)
    // so this annotation os only used at the user boundary.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdDate;
}
