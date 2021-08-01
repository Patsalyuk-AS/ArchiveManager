package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class DocumentServiceImplTest {

    @Autowired
    DocumentService documentService;


    @Test
    void getDocumentByID() {
    }

    @Test
    void create() {
        Document document = new Document();
        document.setCode("t00001");
        document.setName("Test");
        documentService.create(document);
        assertNotNull(documentService.findByCode("t00001"));
    }

    @Test
    void update() {
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