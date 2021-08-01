package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class DocumentServiceImplTest {

    @Autowired
    DocumentService documentService;


    @Test
    void getDocumentByID() {
        Document document = documentService.getDocumentByID(2L);
        Document document1 = new Document();
        document1.setName("Document2");
        document1.setCode("d000002");
        assertEquals(document1, document);
    }

    @Test
    void create() {
        Document document = new Document();
        document.setCode("t000001");
        document.setName("Test");
        documentService.create(document);
        assertNotNull(documentService.findByCode("t000001"));
    }

    @Test
    void update() {
        Document document = new Document();
        document.setCode("t000003");
        document.setName("DocumentTest3");
        assertEquals("d000003", documentService.getDocumentByID(3L).getCode());
        documentService.update(3L, document);
        assertNotNull(documentService.findByCode("t000003"));
        assertEquals("t000003", documentService.getDocumentByID(3L).getCode());
    }

    @Test
    void getDocumentsInBox() {
    }

    @Test
    void putDocumentInBox() {
    }

    @Test
    void extractDocumentFromBox() {
    }

    @Test
    void findByCode() {
    }
}