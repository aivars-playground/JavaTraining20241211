package org.example.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseParameterisedTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            00:00:00.961236, 0
            00:00:45, 0
            00:23:45, 23
            01:23:45, 83
            """)
    void durationInMinutes(String input, long expected) {
        Course course = new Course("id","title", input, "uri://", false);
        assertEquals(expected, course.durationInMinutes());
    }
}
