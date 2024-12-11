package org.example.service;

public record Course(
        String id,
        String title,
        String duration,
        String contentUrl,
        boolean isRetired
) {}
