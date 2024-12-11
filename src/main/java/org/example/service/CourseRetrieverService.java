package org.example.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseRetrieverService {

    private static final String     PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    private static final HttpClient CLIENT = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public List<Course> retrieveCourse(String courseId) {

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(String.format(PS_URI, courseId)))
                .GET()
                .build();

        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse
                .BodyHandlers
                .ofString();

        try {
            HttpResponse<String> response = CLIENT.send(request, bodyHandler);
            return switch (response.statusCode()) {
                case 200 -> {
                    JavaType returnType = MAPPER
                            .getTypeFactory()
                            .constructCollectionType(List.class, Course.class);
                    yield MAPPER.readValue(response.body(), returnType);
                }
                case 404 -> List.of();
                default -> throw new RuntimeException("Unexpected response code: " + response.statusCode());
            };
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("could not call API",ex);
        }
    }
}
