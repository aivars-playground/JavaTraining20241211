package org.example.server;

import org.example.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

import static org.glassfish.jersey.server.ServerProperties.WADL_FEATURE_DISABLE;

public class CourseServer {

    static {
        //redirecting all logging to SLF4J
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseResource.class);
    private static final String BASE_URI = "http://localhost:8080";

    public static void main(String[] args) {

        LOGGER.info("starting server...");
        String databaseFileName = loadDatabaseFileName();
        LOGGER.info("database file: {}", databaseFileName);

        CourseRepository repository = CourseRepository.openCourseRepository(databaseFileName);

        ResourceConfig config = new ResourceConfig()
                .property(WADL_FEATURE_DISABLE, true)
                .register(new CourseResource(repository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    private static String loadDatabaseFileName() {
        try (InputStream propertiesStream = CourseServer.class.getResourceAsStream("/server.properties")){
            Properties properties = new Properties();
            properties.load(propertiesStream);
            return properties.getProperty("course-info.database");
        } catch (IOException e) {
            throw new IllegalStateException("failed to load properties file", e);
        }
    }
}
