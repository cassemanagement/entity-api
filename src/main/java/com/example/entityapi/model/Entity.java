package com.example.entityapi.model;

import com.example.entityapi.model.entity.attributes.Attribute;
import com.example.entityapi.model.entity.attributes.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.microsoft.spring.data.gremlin.annotation.Vertex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * Entity vertex model.
 */
@Vertex
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity
{
    @Id
    private String id;

    @NotEmpty(message = "Please enter a name")
    private String name;

    @NotEmpty(message = "Please enter a type")
    private String type;

    private String description;

    @Valid
    private List<Attribute> attributes;

    @Valid
    private List<Comment> comments;

    private String createdBy;

    // Date is used as only that is supported by Gremlin.
    // JsonFormat is com.fasterxml.jackson (not org.apache.tinkerpop.shaded.jackson)
    // so this annotation os only used at the user boundary.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdDate;
}
