package com.github.patsalyukas.archivemanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"boxDTO"})
public class DocumentDTO {

    @Schema(description = "ID of Document table")
    private Long id;

    @NonNull
    @Schema(description = "Name of the document")
    private String name;

    @NonNull
    @Schema(description = "Code of the document")
    private String code;

    @Schema(description = "Box with document")
    private BoxDTO boxDTO;

}
