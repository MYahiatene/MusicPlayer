package de.techfak.gse.ymokrane;


import java.io.File;


public final class GSERadio {


    private GSERadio() {

    }

    public static void main(final String... args) {

        if (args.length == 0) {
            File files = new File("");
            ReadPath.readDir(files.getAbsolutePath());
        } else {
            File files = new File(args[0]);
            ReadPath.readDir(args[0]);

        }



        //PlayMusic.play("test");


    }
}


