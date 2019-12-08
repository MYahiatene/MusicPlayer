package de.techfak.gse.ymokrane.controller;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;

import de.techfak.gse.ymokrane.model.Model;
import de.techfak.gse.ymokrane.model.PathParser;
import de.techfak.gse.ymokrane.model.Song;
import javafx.fxml.FXML;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;


public class GSERadioController implements PropertyChangeListener {
    /*default*/ static final int ERROR100 = 100;
    /*default*/ static final String MINUS = " - ";
    @FXML
    /*default*/ TableView<String> tableView;
    @FXML
    /*default*/ Button button1;
    @FXML
    /*default*/ Label label1;
    @FXML
    /*default*/ Label label2;
    @FXML
    /*default*/ Label label3;
    @FXML
    /*default*/ Label label4;
    @FXML
    /*default*/ Label label5;
    @FXML
    /*default*/ Label label6;


    private Model model;


    public GSERadioController(final Model model) {
        this.model = model;
        model.getPlayer().addPropertyChangeListener(this);
    }


    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("newSong")) {
            updateMeta();
        }
    }

    @FXML
        /*default*/ void startPlayer() throws InvalidPathException, NoMp3FilesException {
        List<Song> songList;
        Song firstSong;
        final PathParser parser = model.getParser();
        songList = parser.getObjectList(model.getParser().getPlaylist());
        firstSong = songList.get(0);
        model.getPlayer().playSongs();
        label1.setText(firstSong.getArtist() + MINUS + firstSong.getTitle());
        label2.setText(firstSong.getArtist());
        label3.setText(firstSong.getTitle());
        label4.setText(Long.toString(firstSong.getDuration()));
        label5.setText(firstSong.getAlbum());
        label6.setText(firstSong.getGenre());
        button1.setOnMouseClicked(null);
        //tableView

    }

    /*default*/ void updateMeta() {
        final Song song = model.getPlayer().getNewSong();
        label1.setText(song.getArtist() + MINUS + song.getTitle());
        label2.setText(song.getArtist());
        label3.setText(song.getTitle());
        label4.setText(Long.toString(song.getDuration()));
        label5.setText(song.getAlbum());
        label6.setText(song.getGenre());

    }
}

