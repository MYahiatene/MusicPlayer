package de.techfak.gse.ymokrane.controller;

import de.techfak.gse.ymokrane.model.Model;
import de.techfak.gse.ymokrane.model.MusicPlayer;
import de.techfak.gse.ymokrane.model.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


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
    private MusicPlayer player;

    /**
     * @param model hands over model to constructor.
     */
    public GSERadioController(final Model model) {
        this.model = model;
        model.getPlayer().addPropertyChangeListener(this);
        this.playlist = model.getPlayer().getPlaylist();
        this.songList = model.getParser().getObjectList(playlist);
        this.player = model.getPlayer();
    }


    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("newSong")) {
            updateMeta();
        }
        if (evt.getPropertyName().equals("newPlaylist")) {

            updateTableView();


        }

    }

    @FXML
        /*default*/ void updateTableView() {
        data.clear();
        data.addAll(player.getSongList());
        tableView.setItems(data);

    }

    /*default*/ void updateMeta() {
        final Song tmpsong = new Song(model.getPlayer().getMediaPlayer().media().info().mrl(), 0);
        updateLabel(tmpsong);

    }

    @FXML
        /*default*/ void countVote() {


        player.countVote(dropDown.getSelectionModel().getSelectedItem());

        updateTableView();


    }

    /*default*/ void resetVote() {
        model.getPlayer().resetVote();
        updateTableView();
    }


    @FXML
        /*default*/ void startPlayer() {

        Song firstSong;
        firstSong = songList.get(0);
        player.playSongs();
        updateLabel(firstSong);
        button1.setOnMouseClicked(null);
        fillTableView();


    }

    /*default*/ void updateLabel(final Song song) {
        label1.setText(song.getArtist() + MINUS + song.getTitle());
        label2.setText(song.getArtist());
        label3.setText(song.getTitle());
        label4.setText(Long.toString(song.getDuration()));
        label5.setText(song.getAlbum());
        label6.setText(song.getGenre());

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


}

