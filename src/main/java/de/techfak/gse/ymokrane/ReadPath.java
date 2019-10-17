package de.techfak.gse.ymokrane;


import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class ReadPath {

    public static void readDir(String pfad) {

        File file = new File(pfad);

        List<File> mp3List = new ArrayList<>();


        for (File test : file.listFiles()) {
            if (!test.getAbsoluteFile().isDirectory() && test.getAbsoluteFile().toString().endsWith(".mp3")) {
                mp3List.add(test);
            }
        }
        if (mp3List.isEmpty()) {
            System.out.println("keine mp3s gefunden");
            System.exit(100);
        }

        for (File files : mp3List) {
            System.out.println(files);

        }


    }

}
