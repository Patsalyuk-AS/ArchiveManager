package com.github.patsalyukas.archivemanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoxNotFoundException extends RuntimeException {

    public BoxNotFoundException() {
    }

    public BoxNotFoundException(String message) {
        super(message);
    }

    public BoxNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoxNotFoundException(Throwable cause) {
        super(cause);
    }

    public BoxNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
