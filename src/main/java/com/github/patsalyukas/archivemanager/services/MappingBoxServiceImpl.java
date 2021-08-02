package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.entities.Box;
import org.springframework.stereotype.Service;

@Service
public class MappingBoxServiceImpl implements MappingBoxService {

    @Override
    public Box mapToBoxEntity(BoxDTO boxDTO) {
        return new Box(boxDTO.getName(), boxDTO.getCode());
    }

    @Override
    public BoxDTO mapToBoxDTO(Box box) {
        return new BoxDTO(box.getName(), box.getCode());
    }
}
