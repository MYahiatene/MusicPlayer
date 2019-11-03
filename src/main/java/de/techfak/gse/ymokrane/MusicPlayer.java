package de.techfak.gse.ymokrane;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class MusicPlayer {

   // private String pfad;

    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

    private MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

    /*public MusicPlayer() {

        //this.pfad = pfad;

    }*/

    public void playSong(final String mp3) {

        mediaPlayer.submit(new Runnable() {
            @Override
            public void run() {
                //mediaPlayer.media().play();
            }
        });


    }

}
