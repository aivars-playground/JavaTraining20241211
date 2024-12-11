package org.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseTest {

    @Test
    void constructorWithValidation_success() {
        Course course = new Course("id","title", 11, "uri://");
    }

    @Test
    void constructorWithValidation_exception_null() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Course(null,"title", 11, "uri://"));
        assertEquals("invalid field value", exception.getMessage());
    }

    @Test
    void constructorWithValidation_exception_blank() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Course("   ","title", 11, "uri://"));
        assertEquals("invalid field value", exception.getMessage());
    }

}
