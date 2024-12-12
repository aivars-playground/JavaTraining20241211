package org.example;

import org.example.repository.CourseRepository;
import org.example.service.CourseStorageService;
import org.example.service.PsCourse;
import org.example.service.CourseRetrieverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {

    public static final Logger LOGGER = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Course Retriever");
        if (args.length <1) {
            LOGGER.warn("please provide an author name");
            return;
        }

        try{
            retrieveCourses(args[0]);
        } catch (Exception ex) {
            LOGGER.error("Unexpected error", ex);
        }
    }

    private static void retrieveCourses(String authorId) {
        LOGGER.info("Retrieving courses for authorId: '{}'", authorId);
        CourseRetrieverService courseRetrieverService = new CourseRetrieverService();

        List<PsCourse> activeCourses = courseRetrieverService
                .retrieveCourse(authorId)
                .stream()
                .filter(not(PsCourse::isRetired))
                .toList();

        LOGGER.info("Retrieved {} courses: '{}'", activeCourses.size(), activeCourses);


        CourseRepository repository = CourseRepository.openCourseRepository("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(repository);

        courseStorageService.store(activeCourses);
        LOGGER.info("DONE");
    }
}
