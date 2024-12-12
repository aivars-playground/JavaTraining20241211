package org.example.service;

import org.example.domain.Course;
import org.example.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void store() {
        CourseRepository courseRepository = new InMemoryCourseRepository();
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        PsCourse course = new PsCourse("id","title", "00:00:00.961236", "uri://", false);
        courseStorageService.store(List.of(course));

        Course expected = new Course("id","title",0,"http://localhosturi://", Optional.empty());
        assertEquals(List.of(expected), courseRepository.getAllCourses());
    }

    static class  InMemoryCourseRepository implements CourseRepository {

        private List<Course> courses = new ArrayList<>();

        @Override
        public void saveCourse(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return List.copyOf(courses);
        }

        @Override
        public void addNotes(String id, String notes) {

        }
    }
}
