package de.techfak.gse.ymokrane;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class PathParser {

    private String pfad;

    private List<File> mp3List = new ArrayList<>();

    public PathParser(String[] pfad) {
        if (pfad.length==0){

            this.pfad = "";}
        else

        this.pfad=pfad[0];
    }

    public String getPfad() {


            return pfad;




    }

    public List<File> getPlaylist() {

        File files = new File(pfad);

        if (!files.isDirectory()) {
            System.out.println("ungültiger Pfad");

            System.exit(100);
        }


        for (File file : files.listFiles()) {

            if (!file.getAbsoluteFile().isDirectory() && file.getAbsoluteFile().toString().endsWith(".mp3")) {

                mp3List.add(file);

            }

            if (mp3List.isEmpty()) {

                System.out.println("keine mp3s gefunden");

                System.exit(100);

            }



        }
        Collections.shuffle(mp3List);
        return mp3List;
    }


}


