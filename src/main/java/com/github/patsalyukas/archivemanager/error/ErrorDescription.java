package com.github.patsalyukas.archivemanager.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorDescription {

    REQUEST_FORMAT_CONTROL_NOT_PASS(400, 1, "Не пройден форматно-логический контроль запроса"),
    SYSTEM_UNAVAILABLE( 503, 10, "Система временно недоступна. Требуется повторить запрос.");

    private final int status;
    private final int code;
    private final String description;
}
