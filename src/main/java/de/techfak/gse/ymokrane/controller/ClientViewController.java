package de.techfak.gse.ymokrane.controller;

import de.techfak.gse.ymokrane.model.WebClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;


public class ClientViewController {
    @FXML
    Button button1;

    @FXML
    TextField ip;

    @FXML
    TextField port;

    public void connectServer() throws IOException {
        if (ip.getCharacters().length() != 0 && ip.getCharacters().length() != 0) {
            String[] args = new String[3];
            args[0] = "http://";
            args[1] = ip.getCharacters().toString();
            args[2] = port.getCharacters().toString();
            WebClient.main(args);
        } else {
            throw new IOException("Eingabe falsch");
        }
    }
}
