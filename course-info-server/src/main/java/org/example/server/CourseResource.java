package org.example.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.example.domain.Course;
import org.example.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@Path("/courses")
public class CourseResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    public String getCourses() {
        return courseRepository
                .getAllCourses()
                .stream()
                .map(Course::toString)
                .collect(Collectors.joining(", "));
    }
}
