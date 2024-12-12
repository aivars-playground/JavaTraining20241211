package org.example.server;

import org.example.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.net.URI;
import java.util.logging.LogManager;

public class CourseServer {

    static {
        //redirecting all logging to SLF4J
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseResource.class);
    private static final String BASE_URI = "http://localhost:8080";

    public static void main(String[] args) {
        LOGGER.info("starting server");

        CourseRepository repository = CourseRepository.openCourseRepository("./courses.db");
        ResourceConfig config = new ResourceConfig().register(new CourseResource(repository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }
}
