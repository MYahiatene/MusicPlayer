package de.techfak.gse.ymokrane.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ViewController {


    private PropertyChangeSupport support;

    @FXML
    private Button button1;

    @FXML
    private Label label1;

    public ViewController() {
        //erzeuge neuen PropertyChangeSupport für dieses Objekt
        support = new PropertyChangeSupport(this);
    }


    //Hinzufügen eines Observers bzw. PropertyChangeListeners
    public void addPropertyChangeListener(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    //Entfernen eines Observers bzw. PropertyChangeListeners
    public void removePropertyChangeListener(PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }

    @FXML
    void play() {

        support.firePropertyChange("play", false, true);

    }
}
