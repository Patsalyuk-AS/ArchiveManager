package com.github.patsalyukas.archivemanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"boxDTO"})
public class DocumentDTO {

    @NonNull
    @Schema(description = "Name of the document")
    private String name;

    @NonNull
    @Schema(description = "Code of the document")
    private String code;

    @Schema(description = "Box with document")
    private BoxDTO boxDTO;

}
