package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void durationInMinutesUnderMinuteMillis() {
        Course course = new Course("id","title", "00:00:00.961236", "uri://", false);
        assertEquals(0, course.durationInMinutes());
    }

    @Test
    void durationInMinutesUnderMinute() {
        Course course = new Course("id","title", "00:00:45", "uri://", false);
        assertEquals(0, course.durationInMinutes());
    }

    @Test
    void durationInMinutesUnderHour() {
        Course course = new Course("id","title", "00:23:45", "uri://", false);
        assertEquals(23, course.durationInMinutes());
    }

    @Test
    void durationInMinutesOverHour() {
        Course course = new Course("id","title", "01:23:45", "uri://", false);
        assertEquals(83, course.durationInMinutes());
    }
}
