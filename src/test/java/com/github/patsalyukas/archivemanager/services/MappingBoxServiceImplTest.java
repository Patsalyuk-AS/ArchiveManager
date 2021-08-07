package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.entities.Box;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MappingBoxServiceImplTest {

    @Autowired
    MappingBoxService mappingBoxService;

    @Test
    void mapToBoxEntity() {
        BoxDTO boxDTO = new BoxDTO("box", "b0001");
        Box box = new Box("box", "b0001");
        assertEquals(box, mappingBoxService.mapToBoxEntity(boxDTO));

    }

    @Test
    void mapToBoxDTO() {
        BoxDTO boxDTO = new BoxDTO("box", "b0001");
        Box box = new Box("box", "b0001");
        assertEquals(boxDTO, mappingBoxService.mapToBoxDTO(box));
    }
}