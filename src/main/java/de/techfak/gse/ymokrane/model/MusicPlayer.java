package de.techfak.gse.ymokrane.model;

import java.io.File;
import java.util.List;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;


public class MusicPlayer {


    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

    public MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

    private int playlistIndex = 0;

    private List<File> playlist;


    /**
     * @param playlist Erhält die geshuffelte playlist
     */
    public MusicPlayer(final List<File> playlist) {

        this.playlist = playlist;

    }

    public int getPlaylistIndex() {
        return playlistIndex;
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
                    }
                });
            }

        });


    }

    public List<File> getPlaylist() {
        return playlist;

    }

}
