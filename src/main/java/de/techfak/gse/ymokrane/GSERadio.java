package de.techfak.gse.ymokrane;


public final class GSERadio {


    private GSERadio() {

    }

    public static void main(final String... args) {
        try {
            ReadPath.readDir(args[0]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No file");


        }

        PlayMusic.play("test");

    }
}


