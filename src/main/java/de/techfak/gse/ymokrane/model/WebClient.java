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
    private static final int DELAY_MS = 1000;
    private static final int PERIOD_MS = 10000;
    private PropertyChangeSupport support;

    /**
     * Webclient simple Webclient implementation.
     */
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
                .uri(URI.create(args[0] + args[1] + ":" + args[2] + "/current-song"))
                .build();

            Timer timer = new Timer();
            TimerTask printTimeTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        try {
                            JsonParser parser = new JsonParser();
                            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                            Song song = parser.parseJSON(response.body());
                            String artist = song.getArtist();
                            String title = song.getTitle();

                            support.firePropertyChange("connected", true, false);
                            support.firePropertyChange("get", artist, title);


                        } catch (Exception e) {
                            support.firePropertyChange("not connected", true, false);
                        }

                    });
                }
            };

            timer.schedule(printTimeTask, DELAY_MS, PERIOD_MS);


        } catch (Exception e) {
            int i = 1 + 1;
        }

    }

}


