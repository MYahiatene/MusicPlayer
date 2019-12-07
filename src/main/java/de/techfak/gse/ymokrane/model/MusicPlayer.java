package de.techfak.gse.ymokrane.model;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.List;


public class MusicPlayer {

    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    private MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

    private int playlistIndex = 0;

    private List<File> playlist;
    private PropertyChangeSupport support;


    /**
     * @param playlist Erhält die geshuffelte playlist
     */
    public MusicPlayer(final List<File> playlist) {
        support = new PropertyChangeSupport(this);
        this.playlist = playlist;

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

    public void addPropertyChangeListener(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }


    /**
     * Spielt die Songs der Playlist in repeat ab.
     */
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

                if (playlistIndex < playlist.size() - 1) {
                    playlistIndex += 1;
                } else {
                    playlistIndex = 0;
                }
                playlist.add(playlist.get(0));
                playlist.remove(0);
                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer.media().play(playlist.get(0).toString());
                        support.firePropertyChange("newSong", true, false);


                    }
                });
            }

        });


    }

    public List<File> getPlaylist() {
        return playlist;

    }

}
