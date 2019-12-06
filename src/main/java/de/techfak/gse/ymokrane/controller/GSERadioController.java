package de.techfak.gse.ymokrane.controller;

import de.techfak.gse.ymokrane.model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class GSERadioController implements PropertyChangeListener {

    /*default */ Model model;

    public GSERadioController(final Model model) {
        this.model = model;
    }


    @Override
    public void propertyChange(final PropertyChangeEvent evt) {

    }
}

