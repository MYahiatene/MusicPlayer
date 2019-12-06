package de.techfak.gse.ymokrane;

import java.util.List;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.model.Model;

public final class GSERadio {

    private GSERadio() {

    }

    /**
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        final int error100 = 100;


        try {
            if (args.length != 0 && args[0].equals("--gui") || args[0].equals("-g")) {
                if (args.length >= 2) {
                    args = List.of(args).subList(1, args.length).toArray(new String[0]);
                    GSERadioApplication.main(args);
                }
                if (args.length == 1) {
                    String[] newArgs = new String[0];
                    System.out.println(newArgs.length);
                    GSERadioApplication.main(newArgs);
                } else {
                    Model model = new Model(List.of(args));
                    model.consoleModus();
                }
            }
        } catch (InvalidPathException | NoMp3FilesException e) {
            e.printStackTrace();
            System.exit(error100);
        }
    }
}
