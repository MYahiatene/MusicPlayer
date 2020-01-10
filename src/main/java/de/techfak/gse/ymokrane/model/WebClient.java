package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.model.server.JsonParser;
import javafx.application.Platform;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Timer;
import java.util.TimerTask;


public final class WebClient {

    private PropertyChangeSupport support;
    private static final int DELAY_MS = 1000;
    private static final int PERIOD_MS = 2000;

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


        try {


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(args[0] + args[1] + ":" + args[2]))
                .build();


            Timer timer = new Timer();
            TimerTask printTimeTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        try {
                            JsonParser parser = new JsonParser();
                            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                            support.firePropertyChange("id", 101, parser.parseJSON(response.body()).getId());
                            support.firePropertyChange("artist", 101, parser.parseJSON(response.body()).getArtist());
                            support.firePropertyChange("title", 101, parser.parseJSON(response.body()).getTitle());
                            support.firePropertyChange("connected", true, false);

                        } catch (Exception e) {
                            support.firePropertyChange("not connected", true, false);
                        }

                    });
                }
            };

            timer.schedule(printTimeTask, DELAY_MS, PERIOD_MS);


        } catch (Exception e) {

        }

    }

}


