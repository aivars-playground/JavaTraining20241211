package org.example.repository;

import org.example.domain.Course;
import org.example.repository.impl.CourseJdbcRepository;

import java.util.List;

public interface CourseRepository {

    void saveCourse(Course course);

    List<Course> getAllCourses();

    void addNotes(String id, String notes);

    static CourseRepository openCourseRepository(String databaseFile) {
        return new CourseJdbcRepository(databaseFile);
    }
}
