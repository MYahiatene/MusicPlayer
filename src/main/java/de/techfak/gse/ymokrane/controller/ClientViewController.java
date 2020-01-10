package de.techfak.gse.ymokrane.controller;

import de.techfak.gse.ymokrane.model.WebClient;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Timer;


public class ClientViewController implements PropertyChangeListener {
    private static final int DELAY_MS = 1000;
    private static final int PERIOD_MS = 2000;

    @FXML
    Button button1;

    @FXML
    TextField ip;

    @FXML
    TextField port;

    @FXML
    Label connectionStatus;

    @FXML
    Label serverSatus;
    @FXML
    Label currentSong;


    String[] args;
    WebClient client = new WebClient();

    /**
     * @throws IOException throes output exception on error
     */
    public void connectServer() throws IOException {
        final int number3 = 3;
        if (ip.getCharacters().length() != 0 && port.getCharacters().length() != 0) {

            client.addPropertyChangeListener(this);
            this.args = new String[number3];
            args[0] = "http://";
            args[1] = ip.getCharacters().toString();
            args[2] = port.getCharacters().toString();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    client.client(args);
                }
            });

            Timer timer = new Timer();

        } else {
            throw new IOException("Eingabe falsch");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if (evt.getPropertyName().equals("connected")) {
                connectionStatus.setText("connection established!");

            }


            if (evt.getPropertyName().equals("not connected")) {
                connectionStatus.setText("disconnected!");
            }
            if (evt.getPropertyName().equals("get")) {
                currentSong.setText(evt.getOldValue().toString() + "-" + evt.getNewValue());

            }
        });


    }
}
