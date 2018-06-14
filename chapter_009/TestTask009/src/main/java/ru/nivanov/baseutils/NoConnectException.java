package ru.nivanov.baseutils;

/**
 * Created by Nikolay Ivanov on 14.06.2018.
 */
public class NoConnectException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     * @param message the detail message. The detail message is saved for
     * later retrieval by the {@link #getMessage()} method.
     */
    public NoConnectException(String message) {
        super(message);
    }
}
