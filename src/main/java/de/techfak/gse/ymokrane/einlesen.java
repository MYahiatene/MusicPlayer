package de.techfak.gse.ymokrane;

import java.io.File;
import java.io.IOException;

public class einlesen {
    public static void readDir(String pfad){
        File reader = new File(pfad);
        if (reader.isDirectory()){System.out.println("Ist Folder"); } else {
            System.out.println("Ist kein Folder");
        }
    }

}
