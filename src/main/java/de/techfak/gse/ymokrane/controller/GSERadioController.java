package de.techfak.gse.ymokrane.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import de.techfak.gse.ymokrane.model.Model;
import de.techfak.gse.ymokrane.model.Song;


public class GSERadioController implements PropertyChangeListener {
    /*default*/ static final int ERROR100 = 100;

    /*default*/ static final String MINUS = " - ";

    @FXML
    /*default*/ Button voteButton;

    @FXML
    /*default*/ ComboBox<String> dropDown;

    @FXML
    /*default*/ TableView<Song> tableView;

    @FXML
    /*default*/ TableColumn<Song, String> column1;

    @FXML
    /*default*/ TableColumn<Song, String> column2;

    @FXML
    /*default*/ TableColumn<Song, Integer> column3;

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

    private ObservableList<Song> data = FXCollections.observableArrayList();

    private List<File> playlist;

    private List<Song> songList;

    /**
     * @param model hands over model to constructor.
     */
    public GSERadioController(final Model model) {
        this.model = model;
        model.getPlayer().addPropertyChangeListener(this);
        this.playlist = model.getPlayer().getPlaylist();
        this.songList = model.getParser().getObjectList(playlist);
    }


    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("newSong")) {
            updateMeta();
        }
        if (evt.getPropertyName().equals("newPlaylist")) {

            updateTableView();

        }
        if (evt.getPropertyName().equals("resetVote")) {
            // resetVote();

        }
    }

    @FXML
        /*default*/ void updateTableView() {
        data.clear();
        List<File> playlist2 = model.getPlayer().getPlaylist();
        data.addAll(model.getParser().getObjectList(playlist2));
        tableView.setItems(data);

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

    @FXML
        /*default*/ void startPlayer() {

        Song firstSong;
        firstSong = songList.get(0);
        model.getPlayer().playSongs();
        label1.setText(firstSong.getArtist() + MINUS + firstSong.getTitle());
        label2.setText(firstSong.getArtist());
        label3.setText(firstSong.getTitle());
        label4.setText(Long.toString(firstSong.getDuration()));
        label5.setText(firstSong.getAlbum());
        label6.setText(firstSong.getGenre());
        button1.setOnMouseClicked(null);
        fillTableView();


    }

    /*default*/ void fillTableView() {
        column1.setCellValueFactory(new PropertyValueFactory<Song, String>("artist"));
        column2.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        column3.setCellValueFactory(new PropertyValueFactory<Song, Integer>("votes"));
        dropDown.getItems().addAll(fillDrop());
        data.clear();
        data.addAll(songList);
        tableView.setItems(data);

    }

    /*default*/ List<String> fillDrop() {
        final List<String> dropList = new ArrayList<>();
        for (final Song song : songList) {
            dropList.add(song.getArtist() + MINUS + song.getTitle());
        }
        return dropList;
    }

    @FXML
        /*default*/ void countVote() {
        this.songList = model.getPlayer().getSongList();
        for (final Song song : songList) {
            if ((song.getArtist() + MINUS + song.getTitle()).equals(dropDown.getSelectionModel().getSelectedItem())) {
                song.setVotes(song.getVotes() + 1);
            }

        }

        data.clear();
        this.playlist = model.getPlayer().getPlaylist();
        model.getPlayer().setSongList(songList);
        data.addAll(model.getPlayer().getSongList());
        tableView.setItems(data);

    }

    List<Song> resetVote() {
        this.songList.get(0).setVotes(0);
        return songList;
    }
}

