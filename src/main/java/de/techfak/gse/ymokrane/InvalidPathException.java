package de.techfak.gse.ymokrane;


public class InvalidPathException extends Exception {
    private static final long serialVersionUID = 12345L;

    public InvalidPathException(final String message) {
        super(message);
    }
}
