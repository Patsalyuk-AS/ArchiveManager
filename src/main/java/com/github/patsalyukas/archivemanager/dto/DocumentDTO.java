package com.github.patsalyukas.archivemanager.dto;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

    @NonNull
    private String name;
    @NonNull
    private String code;
    private BoxDTO boxDTO;

}
