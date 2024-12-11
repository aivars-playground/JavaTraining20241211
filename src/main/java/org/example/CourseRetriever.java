package org.example;

import org.example.service.CourseRetrieverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        String course = courseRetrieverService.retrieveCourse(authorId);
        LOGGER.info("Retrieved course: '{}'", course);
    }
}
