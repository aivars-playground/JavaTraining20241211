package org.example.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrieverService {

    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    private static final HttpClient CLIENT = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    public String retrieveCourse(String courseId) {

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
                case 200 -> response.body();
                case 404 -> "";
                default -> throw new RuntimeException("Unexpected response code: " + response.statusCode());
            };
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException("could not call API",ex);
        }
    }
}
