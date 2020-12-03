package com.example.entityapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController
{

    @Value("${git.build.time}")
    private String buildTime;

    @Value("${git.build.version}")
    private String version;

    @Value("${git.commit.id.abbrev}")
    private String hash;

    @GetMapping
    public String home()
    {
        return String.format("Entity API version %s. Build time %s. Hash: %s",
                             version,
                             buildTime,
                             hash
        );
    }
}
