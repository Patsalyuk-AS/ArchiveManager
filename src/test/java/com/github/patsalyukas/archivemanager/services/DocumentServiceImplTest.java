package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.exceptions.DocumentExist;
import com.github.patsalyukas.archivemanager.exceptions.DocumentNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DocumentServiceImplTest {

    @Autowired
    DocumentService documentService;

    @Autowired
    BoxService boxService;

    private final RowMapper<Long> idMapper = ((resultSet, rowNum) -> resultSet.getLong("id"));
    private final RowMapper<Box> boxMapper = (((resultSet, rowNum) -> new Box(resultSet.getString("name"), resultSet.getString("code"))));
    private final RowMapper<Document> documentMapper = (((resultSet, rowNum) -> new Document(resultSet.getString("name"), resultSet.getString("code"))));
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void getDocumentByID() {
        jdbcTemplate.query("SELECT * FROM DOCUMENTS", idMapper).stream().limit(20).forEach(id -> {
            Document realDocument = documentService.findDocumentById(id);
            Document expectedDocument = jdbcTemplate.queryForObject("SELECT * FROM DOCUMENTS WHERE id = ?", documentMapper, id);
            assertEquals(expectedDocument, realDocument);
        });
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
        Random random = new Random();
        List<Long> ids = jdbcTemplate.query("SELECT * FROM DOCUMENTS", idMapper);
        Long id = ids.get(random.nextInt(ids.size()));
        Document document = new Document("DocumentTest3", "t000003");
        Document documentFromDB = jdbcTemplate.queryForObject("SELECT * FROM DOCUMENTS WHERE id = ?", documentMapper, id);
        assertEquals(documentFromDB, documentService.findDocumentById(id));
        documentService.update(id, document);
        assertNotNull(documentService.findByCode("t000003"));
        assertEquals("t000003", documentService.findDocumentById(id).getCode());
        Document notExistDocument = new Document("Test", "t000001");
        Long notExistId = ids.get(ids.size() - 1) + 20;
        assertThrows(DocumentNotFoundException.class, () -> documentService.update(notExistId, notExistDocument));
    }

    @Test
    void getDocumentsInBox() {
        Random random = new Random();
        List<Long> ids = jdbcTemplate.query("SELECT * FROM BOXES", idMapper);
        Long id = ids.get(random.nextInt(ids.size()));
        List<Document> expectedDocuments = jdbcTemplate.query("SELECT * FROM DOCUMENTS WHERE BOX = ?", documentMapper, id);
        List<Document> documents = documentService.getDocumentsInBox(id);
        assertEquals(expectedDocuments.size(), documents.size());
        expectedDocuments.forEach(document -> assertTrue(documents.contains(document)));
    }

    @Test
    void putDocumentInBox() {
        List<Long> ids = jdbcTemplate.query("SELECT * FROM BOXES", idMapper);
        Document document = new Document("Test", "t000001");
        documentService.create(document);
        assertNull(documentService.findByCode("t000001").getBox());
        documentService.putDocumentInBox(ids.get(0), document);
        List<Document> documents = documentService.getDocumentsInBox(ids.get(0));
        assertTrue(documents.contains(document));
    }

    @Test
    void extractDocumentFromBox() {
        Long id = getId();
        Document document = documentService.findDocumentById(id);
        Box box = document.getBox();
        List<Document> documents = documentService.getDocumentsInBox(box.getId());
        assertTrue(documents.contains(document));
        documentService.extractDocumentFromBox(id);
        documents = documentService.getDocumentsInBox(box.getId());
        assertFalse(documents.contains(document));
    }

    @Test
    void findByCode() {
        Document document = new Document("Document6", "d000006");
        assertEquals(document, documentService.findByCode("d000006"));
    }

    private Long getId() {
        Random random = new Random();
        List<Long> ids = jdbcTemplate.query("SELECT * FROM DOCUMENTS", idMapper);
        return ids.get(random.nextInt(ids.size()));
    }
}