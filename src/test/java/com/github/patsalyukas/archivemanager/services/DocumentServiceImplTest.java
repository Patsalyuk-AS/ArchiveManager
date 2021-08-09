package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.exceptions.DocumentExist;
import com.github.patsalyukas.archivemanager.exceptions.DocumentNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DocumentServiceImplTest {

    @Autowired
    DocumentService documentService;

    @Autowired
    BoxService boxService;


    @Test
    void getDocumentByID() {
        Document document = documentService.findDocumentByID(2L);
        Document documentTest = new Document("Document2", "d000002");
        assertEquals(documentTest, document);
    }

    @Test
    void create() {
        Document document = new Document("Test", "t000001");
        documentService.create(document);
        assertNotNull(documentService.findByCode("t000001"));
        Document existDocument = new Document("Test", "t000001");
        assertThrows(DocumentExist.class, () -> documentService.create(existDocument));
    }

    @Test
    void update() {
        Document document = new Document("DocumentTest3", "t000003");
        assertEquals("d000003", documentService.findDocumentByID(3L).getCode());
        documentService.update(3L, document);
        assertNotNull(documentService.findByCode("t000003"));
        assertEquals("t000003", documentService.findDocumentByID(3L).getCode());
        Document notExistDocument = new Document("Test", "t000001");
        assertThrows(DocumentNotFoundException.class, () -> documentService.update(20L, notExistDocument));
    }

    @Test
    void getDocumentsInBox() {
        List<Document> documents = documentService.getDocumentsInBox(1L);
        assertTrue(documents.contains(new Document("Document1", "d000001")));
        assertTrue(documents.contains(new Document("Document5", "d000005")));
        assertTrue(documents.contains(new Document("Document7", "d000007")));
        assertFalse(documents.contains(new Document("Document2", "d000002")));
    }

    @Test
    void putDocumentInBox() {
        Document document = new Document("Test", "t000001");
        documentService.create(document);
        assertNull(documentService.findByCode("t000001").getBox());
        documentService.putDocumentInBox(1L, document);
        List<Document> documents = documentService.getDocumentsInBox(1L);
        assertTrue(documents.contains(document));
    }

    @Test
    void extractDocumentFromBox() {
        Document document = documentService.findDocumentByID(2L);
        Box box = document.getBox();
        List<Document> documents = documentService.getDocumentsInBox(box.getId());
        assertTrue(documents.contains(document));
        documentService.extractDocumentFromBox(2L);
        documents = documentService.getDocumentsInBox(box.getId());
        assertFalse(documents.contains(document));
    }

    @Test
    void findByCode() {
        Document document = new Document("Document6", "d000006");
        assertEquals(document, documentService.findByCode("d000006"));
    }
}