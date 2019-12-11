package de.techfak.gse.ymokrane.model;

import javafx.application.Platform;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MusicPlayer {

    /*default*/ Song newSong;

    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

    private MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

    private int playlistIndex = 0;

    private List<Song> songList;
    private List<File> playlist;

    private PropertyChangeSupport support;

    private PathParser parser;


    /**
     * @param playlist Erhält die geshuffelte playlist
     */
    public MusicPlayer(final List<File> playlist) {
        support = new PropertyChangeSupport(this);
        this.playlist = playlist;

        this.parser = new PathParser(convertFiletoPlaylist());
        this.songList = parser.getObjectList(playlist);
    }

    /*default*/ List<String> convertFiletoPlaylist() {
        final List<String> stringPlaylist = new ArrayList<>();
        for (final File file : playlist) {
            stringPlaylist.add(file.toString());
        }
        return stringPlaylist;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public int getPlaylistIndex() {
        return playlistIndex;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }

    public void addPropertyChangeListener(final PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(final PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }

    public List<Song> getSongList() {
        return songList;
    }

    public Song getNewSong() {
        return newSong;
    }

    public void setSongList(final List<Song> songList) {
        this.songList = songList;
    }

    public void playSongs() {


        mediaPlayer.submit(new Runnable() {
            @Override
            public void run() {

                mediaPlayer.media().play(playlist.get(0).toString());
            }
        });

        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            /**
             * @param mediaPlayer
             */
            @Override
            public void finished(final MediaPlayer mediaPlayer) {

                manLists();

                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {


                        mediaPlayer.media().play(getPlaylist().get(0).toString());
                        resetVote();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                /*
                                //support.firePropertyChange("resetVote", true, false);
                                if (playlistIndex <songList.size() - 1) {
                                    playlistIndex += 1;
                                } else {
                                    playlistIndex = 0;
                                }
                                */
                                support.firePropertyChange("newSong", true, false);
                                support.firePropertyChange("newPlaylist", true, false);


                            }
                        });

                    }
                });
            }

        });


    }

    public void setPlaylist(final List<File> playlist) {
        this.playlist = playlist;
    }

    public List<File> getPlaylist() {
        return this.playlist;

    }

    /*default*/
    public void countVote(String dropDown) {
        final String MINUS = " - ";
        for (final Song song : songList) {
            String tmp = song.getArtist() + MINUS + song.getTitle();
            if (tmp.equals(dropDown)) {
                song.setVotes(song.getVotes() + 1);
            }
        }

        Collections.sort(songList, new Comparator<Song>() {
            @Override
            public int compare(final Song song, final Song t1) {
                return song.compareTo(t1);
            }
        });

        this.playlist = parser.getPlaylistFromSong(songList);


    }

    /*default*/
    public void resetVote() {
        songList.get(0).setVotes(0);

    }

    void manLists() {
        songList.add(songList.get(0));
        songList.remove(0);
        playlist.add(playlist.get(0));
        playlist.remove(0);
    }

    Song getCurrentSong() {
        return null;
    }

}
