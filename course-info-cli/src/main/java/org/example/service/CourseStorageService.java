package org.example.service;

import org.example.domain.Course;
import org.example.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {

    private static final String BASE_URL = "http://localhost";

    private final CourseRepository courseRepository;

    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void store(List<PsCourse> courses) {
        for (PsCourse psCourse : courses) {
            Course course = new Course(
                    psCourse.id(),
                    psCourse.title(),
                    psCourse.durationInMinutes(),
                    BASE_URL + psCourse.contentUrl(),
                    Optional.empty());
            courseRepository.saveCourse(course);
        }
    }

}
