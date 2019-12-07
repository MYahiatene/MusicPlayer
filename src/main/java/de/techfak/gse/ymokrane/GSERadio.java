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
    public static void main(final String[] args) {
        final int error100 = 100;
        final String gui = "--gui";
        final String gui2 = "-g";
        String[] newArgs = args;
        try {
            if (!List.of(newArgs).contains(gui) && !List.of(newArgs).contains(gui2)) {
                final Model model = new Model(List.of(newArgs));
                model.consoleModus();
            } else if (newArgs.length != 0 && newArgs[0].equals(gui) || newArgs[0].equals(gui2)) {
                if (newArgs.length >= 2) {
                    newArgs = List.of(newArgs).subList(1, newArgs.length).toArray(new String[0]);
                    GSERadioApplication.main(newArgs);
                } else if (newArgs.length == 1) {
                    newArgs = new String[0];
                    System.out.println(newArgs.length);
                    GSERadioApplication.main(newArgs);
                } else {
                    final Model model = new Model(List.of(newArgs));
                    model.consoleModus();
                }
            }
        } catch (InvalidPathException | NoMp3FilesException e) {
            e.printStackTrace();
            System.exit(error100);
        }
    }
}
