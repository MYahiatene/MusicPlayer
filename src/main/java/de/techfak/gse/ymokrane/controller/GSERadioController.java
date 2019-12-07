package de.techfak.gse.ymokrane.controller;

import de.techfak.gse.ymokrane.GSERadioApplication;
import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;

import de.techfak.gse.ymokrane.model.Song;
import javafx.fxml.FXML;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class GSERadioController implements PropertyChangeListener {
    @FXML
    Button button1;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label label4;
    @FXML
    Label label5;
    @FXML
    Label label6;

    public GSERadioController() {
        GSERadioApplication.model.getPlayer().addPropertyChangeListener(this);
    }


    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        try {
            if (evt.getPropertyName().equals("newSong")) {
                updateMeta();
            }
        } catch (InvalidPathException | NoMp3FilesException e) {
            e.getStackTrace();
            System.exit(100);
        }
    }

    @FXML
    void startPlayer() throws InvalidPathException, NoMp3FilesException {

        Song firstSong = GSERadioApplication.model.getParser().getObjectList(GSERadioApplication.model.getParser().getPlaylist()).get(0);
        GSERadioApplication.model.getPlayer().playSongs();
        label1.setText(firstSong.getArtist() + " - " + firstSong.getTitle());
        label2.setText(firstSong.getArtist());
        label3.setText(firstSong.getTitle());
        label4.setText(Long.toString(firstSong.getDuration()));
        label5.setText(firstSong.getAlbum());
        label6.setText(firstSong.getGenre());
        button1.setOnMouseClicked(null);

    }

    //@FXML
    void updateMeta() throws InvalidPathException, NoMp3FilesException {
        int index = GSERadioApplication.model.getPlayer().getPlaylistIndex();
        List<File> playlist = new ArrayList<>();
        playlist = GSERadioApplication.model.getParser().getPlaylist();
        Song song = GSERadioApplication.model.getParser().getObjectList(playlist).get(index);
        label1.setText(song.getArtist() + " - " + song.getTitle());
        label2.setText(song.getArtist());
        label3.setText(song.getTitle());
        label4.setText(Long.toString(song.getDuration()));
        label5.setText(song.getAlbum());
        label6.setText(song.getGenre());

    }
}

