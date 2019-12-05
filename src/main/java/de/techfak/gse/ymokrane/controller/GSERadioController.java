package de.techfak.gse.ymokrane.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import de.techfak.gse.ymokrane.model.Model;


public class GSERadioController implements PropertyChangeListener {

    Model model;

    public GSERadioController(Model model) {
        this.model = model;
    }


    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

    }
}

