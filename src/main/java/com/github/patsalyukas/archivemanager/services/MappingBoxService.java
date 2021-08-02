package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.entities.Box;

public interface MappingBoxService {

    Box mapToBoxEntity(BoxDTO boxDTO);

    BoxDTO mapToBoxDTO(Box box);

}
