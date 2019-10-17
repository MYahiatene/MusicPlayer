package de.techfak.gse.ymokrane;

public final class GSERadio {

    private GSERadio() {
    }

    public static void main(final String... args) {
        einlesen.readDir(args[1]);
        System.out.println("Hello ymokrane!");
    }

}
