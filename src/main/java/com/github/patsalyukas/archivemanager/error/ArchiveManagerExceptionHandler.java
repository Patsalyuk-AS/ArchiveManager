package com.github.patsalyukas.archivemanager.error;

import com.github.patsalyukas.archivemanager.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.github.patsalyukas.archivemanager.error.ErrorDescription.REQUEST_FORMAT_CONTROL_NOT_PASS;
import static com.github.patsalyukas.archivemanager.error.ErrorDescription.SYSTEM_UNAVAILABLE;

@Slf4j
@ControllerAdvice
public class ArchiveManagerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorDTO> handleException(IllegalArgumentException e) {
        log.error("IllegalArgumentException: ", e);
        return buildResponse(REQUEST_FORMAT_CONTROL_NOT_PASS);
    }

    @ExceptionHandler(TooManyRequestsException.class)
    protected ResponseEntity<Void> handleException(TooManyRequestsException e) {
        log.error("Too many request!!!");
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDTO> handleException(Exception e) {
        log.error("Exception: ", e);
        return buildResponse(SYSTEM_UNAVAILABLE);
    }

    private ResponseEntity<ErrorDTO> buildResponse(ErrorDescription error) {
        return ResponseEntity.status(error.getStatus())
                .body(
                        ErrorDTO.builder()
                                .errorCode(error.getCode())
                                .errorDescription(error.getDescription())
                                .build()
                );
    }
}
