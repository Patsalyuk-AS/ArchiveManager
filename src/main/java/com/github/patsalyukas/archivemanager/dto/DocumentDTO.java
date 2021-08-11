package com.github.patsalyukas.archivemanager.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"boxDTO"})
public class DocumentDTO {

    @NonNull
    private java.lang.String name;
    @NonNull
    private java.lang.String code;
    private BoxDTO boxDTO;

}
