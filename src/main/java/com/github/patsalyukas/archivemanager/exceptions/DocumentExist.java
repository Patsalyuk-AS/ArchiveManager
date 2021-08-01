package com.github.patsalyukas.archivemanager.exceptions;

public class DocumentExist extends RuntimeException {

    public DocumentExist() {
    }

    public DocumentExist(String message) {
        super(message);
    }

    public DocumentExist(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentExist(Throwable cause) {
        super(cause);
    }

    public DocumentExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
