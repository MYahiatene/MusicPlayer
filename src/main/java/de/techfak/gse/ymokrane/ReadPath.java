package de.techfak.gse.ymokrane;


import java.io.File;
import java.io.FileReader;



public class ReadPath {

    public static void readDir(String pfad) {

        File files = new File(pfad);
        for (File file : files.listFiles()) {System.out.println(file); }





    }

}
