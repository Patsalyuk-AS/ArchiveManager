package com.github.patsalyukas.archivemanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoxDTO {

    @NonNull
    @Schema(description = "Name of the box")
    private String name;

    @NonNull
    @Schema(description = "Code of the box")
    private String code;

}
