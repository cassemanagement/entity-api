package com.example.entityapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.microsoft.spring.data.gremlin.annotation.Edge;
import com.microsoft.spring.data.gremlin.annotation.EdgeFrom;
import com.microsoft.spring.data.gremlin.annotation.EdgeTo;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity edge model.
 */
@Edge
@Data
public class EntityLink
{
    @Id
    private String id;

    private String name;

    @EdgeFrom
    @NotNull(message = "Please provide start vertex")
    private Entity from;

    @EdgeTo
    @NotNull(message = "Please provide end vertex")
    private Entity to;

    private Map<String, Object> properties = new HashMap<>();

    private String createdBy;

    // Date is used as only that is supported by Gremlin.
    // JsonFormat is com.fasterxml.jackson (not org.apache.tinkerpop.shaded.jackson)
    // so this annotation os only used at the user boundary.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdDate;
}
