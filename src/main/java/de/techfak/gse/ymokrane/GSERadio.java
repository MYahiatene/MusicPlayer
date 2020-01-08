package de.techfak.gse.ymokrane;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.exceptions.WrongPortException;
import de.techfak.gse.ymokrane.model.Model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class GSERadio {

    private GSERadio() {
    }

    /**
     * @param args command line arguments.
     */
    public static void main(final String[] args) throws IOException, InterruptedException {
        final int error100 = 100;
        final int error102 = 102;
        if (args.length==0){
            System.out.println("No options specified!");
            return;
        }
        try {
            final String[] newargs = Arrays.copyOfRange(args, 1, args.length);
            final Model model = new Model(newargs);
            model.optionHandler(args);
        } catch (InvalidPathException | NoMp3FilesException e) {
            e.printStackTrace();
            System.exit(error100);
        } catch (WrongPortException e) {
            e.printStackTrace();
            System.exit(error102);
        }


    }
}
