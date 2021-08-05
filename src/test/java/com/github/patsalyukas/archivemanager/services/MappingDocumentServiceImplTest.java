package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.entities.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MappingDocumentServiceImplTest {

    @Autowired
    MappingDocumentService mappingDocumentService;

    @Test
    void mapToDocumentEntity() {
        Document document = new Document("document1", "d00001");
        DocumentDTO documentDTO = new DocumentDTO("document1", "d00001");
        assertEquals(document, mappingDocumentService.mapToDocumentEntity(documentDTO));
        Box box = new Box("box1", "b00001");
        BoxDTO boxDTO = new BoxDTO("box1", "b00001");
        document.setBox(box);
        documentDTO.setBoxDTO(boxDTO);
        assertEquals(document, mappingDocumentService.mapToDocumentEntity(documentDTO));
    }

    @Test
    void mapToDocumentDTO() {
        Document document = new Document("document1", "d00001");
        DocumentDTO documentDTO = new DocumentDTO("document1", "d00001");
        assertEquals(documentDTO, mappingDocumentService.mapToDocumentDTO(document));
        Box box = new Box("box1", "b00001");
        BoxDTO boxDTO = new BoxDTO("box1", "b00001");
        document.setBox(box);
        documentDTO.setBoxDTO(boxDTO);
        assertEquals(documentDTO, mappingDocumentService.mapToDocumentDTO(document));
    }
}