package org.example.server;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.domain.Course;
import org.example.repository.CourseRepository;
import org.example.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.stream.Stream;

@Path("/courses")
public class CourseResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Course> getCourses() {
        try {
            return courseRepository
                    .getAllCourses()
                    .stream()
                    .sorted(Comparator.comparing(Course::id));
        } catch (RepositoryException rex) {
            LOGGER.error("REPO ERROR", rex);
            throw new NotFoundException(); //this will show 404
        }

    }

    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNote(@PathParam("id") String id, String notes) {
        LOGGER.info("add notes to {}, note:`{}`", id, notes);
        courseRepository.addNotes(id,notes);
    }
}
