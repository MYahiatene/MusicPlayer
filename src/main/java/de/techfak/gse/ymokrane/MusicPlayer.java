package de.techfak.gse.ymokrane;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import java.io.File;
import java.util.List;

public class MusicPlayer {

    private int playlistIndex = 0;
    private List<File> playlist;


    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

    private MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();


    public MusicPlayer(final List<File> playlist) {

        this.playlist = playlist;

    }


    public void playSongs() {


        mediaPlayer.submit(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.media().play(playlist.get(playlistIndex).toString());
            }
        });

        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {

            @Override
            public void finished(final MediaPlayer mediaPlayer) {
                if (playlistIndex < playlist.size() - 1) {
                    playlistIndex += 1;
                } else {
                    playlistIndex = 0;
                }
                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer.media().play(playlist.get(playlistIndex).toString());
                    }
                });
            }

        });


    }
}
