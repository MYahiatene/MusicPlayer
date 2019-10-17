package de.techfak.gse.ymokrane;

import java.io.File;

import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
import uk.co.caprica.vlcj.player.component.MediaPlayerComponent;

public class PlayMusic {

    public static void play(String pfad){

        AudioPlayerComponent MediaPlayerComponent  = new AudioPlayerComponent();
        MediaPlayerComponent.mediaPlayer().media().play("/homes/ymokrane/mp3_folder/Dead_Combo_-_01_-_Povo_Que_Cas_Descalo.mp3");

        try {
        Thread.currentThread().join();
    }
        catch(InterruptedException e) {
    }
}

}

