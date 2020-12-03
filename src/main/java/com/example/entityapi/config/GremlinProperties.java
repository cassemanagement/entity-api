package com.example.entityapi.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tinkerpop.gremlin.driver.ser.Serializers;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Model for gremlin database config. See README for usage.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "gremlin")
class GremlinProperties
{
    private String endpoint;

    private int port;

    private String username;

    private String password;

    private boolean sslEnabled;

    private boolean telemetryAllowed = true;

    private String serializer = Serializers.GRAPHSON.toString();

    private int maxContentLength;
}
