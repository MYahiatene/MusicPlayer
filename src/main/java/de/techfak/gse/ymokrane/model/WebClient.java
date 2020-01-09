package de.techfak.gse.ymokrane.model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public final class WebClient {


    public WebClient() {
    }

    /**
     * Queries and prints christmas markets in Berlin.
     *
     * @param args commandline arguments given on execution
     */
    public static void main(final String... args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(args[0] + args[1] + ":" + args[2]))
            .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("HTTP-Status: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error getting resource");
        }
    }

}


