package com.github.patsalyukas.archivemanager.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class ErrorDTO {

    private final int errorCode;

    private final String errorDescription;
}
