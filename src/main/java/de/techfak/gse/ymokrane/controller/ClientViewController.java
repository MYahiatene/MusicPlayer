package de.techfak.gse.ymokrane.controller;

import de.techfak.gse.ymokrane.model.WebClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;


public class ClientViewController implements PropertyChangeListener {
    @FXML
    Button button1;

    @FXML
    TextField ip;

    @FXML
    TextField port;

    @FXML
    Label connectionStatus;

    public void connectServer() throws IOException {

        if (ip.getCharacters().length() != 0 && port.getCharacters().length() != 0) {
            WebClient client = new WebClient();
            client.addPropertyChangeListener(this);
            String[] args = new String[3];
            args[0] = "http://";
            args[1] = ip.getCharacters().toString();
            args[2] = port.getCharacters().toString();
            client.client(args);

        } else {
            throw new IOException("Eingabe falsch");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("connected")) {
            connectionStatus.setText("connection established!");
        }

    }
}
