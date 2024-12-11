package org.example.domain;

public record Course(
        String id,
        String name,
        long length,
        String url
) {

    public Course {
        filled(id);
        filled(name);
        filled(url);
    }

    private static void filled(String s) {
        if(s == null || s.isBlank()) {
            throw new IllegalArgumentException("invalid field value");
        }
    }
}
