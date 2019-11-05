package de.techfak.gse.ymokrane;


public class NoMp3FilesException extends Exception {
    private static final long serialVersionUID = 1234L;

    public NoMp3FilesException(final String message) {
        super(message);
    }
}
