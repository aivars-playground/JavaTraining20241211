package org.example.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Course(
        String id,
        String title,
        String duration,
        String contentUrl,
        boolean isRetired
) {}
