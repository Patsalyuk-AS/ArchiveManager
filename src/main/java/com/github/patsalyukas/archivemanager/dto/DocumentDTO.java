package com.github.patsalyukas.archivemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

    @NonNull
    private String name;
    @NonNull
    private String code;
    private BoxDTO boxDTO;

}
