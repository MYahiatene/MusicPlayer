package de.techfak.gse.ymokrane.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public final class WebClient {

    private PropertyChangeSupport support;

    public WebClient() {

        this.support = new PropertyChangeSupport(this);

    }

    public void addPropertyChangeListener(final PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }


    /**
     * Queries and prints christmas markets in Berlin.
     *
     * @param args commandline arguments given on execution
     */
    public void client(final String... args) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(args[0] + args[1] + ":" + args[2]))
            .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                support.firePropertyChange("not connected", true, false);
            }
            if (response.statusCode() == 200) {
                support.firePropertyChange("connected", true, false);
            }
            System.out.println("HTTP-Status: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error getting resource");
        }
    }

}


