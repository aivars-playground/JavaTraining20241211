package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void durationInMinutesUnderMinuteMillis() {
        PsCourse course = new PsCourse("id","title", "00:00:00.961236", "uri://", false);
        assertEquals(0, course.durationInMinutes());
    }

    @Test
    void durationInMinutesUnderMinute() {
        PsCourse course = new PsCourse("id","title", "00:00:45", "uri://", false);
        assertEquals(0, course.durationInMinutes());
    }

    @Test
    void durationInMinutesUnderHour() {
        PsCourse course = new PsCourse("id","title", "00:23:45", "uri://", false);
        assertEquals(23, course.durationInMinutes());
    }

    @Test
    void durationInMinutesOverHour() {
        PsCourse course = new PsCourse("id","title", "01:23:45", "uri://", false);
        assertEquals(83, course.durationInMinutes());
    }
}
