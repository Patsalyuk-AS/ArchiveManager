package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.services.BoxService;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import com.github.patsalyukas.archivemanager.services.MappingDocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ExtendWith(SpringExtension.class)
class DocumentControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    DocumentService documentService;

    @Autowired
    DocumentController documentController;

    @Autowired
    MappingDocumentService mappingDocumentService;

    @Autowired
    BoxService boxService;

    @Autowired
    JdbcTemplate template;

    private RowMapper<Long> idMapper = (rs, num) -> rs.getLong("id");

    @Test
    void getDocumentByID() {
        Long goodId = 3L;
        String url = "/documents/%d";
        DocumentDTO documentDTO = new DocumentDTO("Document3", "d000003");
        ResponseEntity<DocumentDTO> goodResponse = testRestTemplate.getForEntity(String.format(url, goodId), DocumentDTO.class);
        assertEquals(documentDTO, goodResponse.getBody());
        assertEquals(HttpStatus.OK, goodResponse.getStatusCode());
        Long badId = 20L;
        ResponseEntity<DocumentDTO> badResponse = testRestTemplate.getForEntity(String.format(url, badId), DocumentDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }

    @Test
    void create() {
        String url = "/documents/";
        DocumentDTO goodDocumentDTO = new DocumentDTO("DocumentTest", "d00000Test");
        HttpEntity<DocumentDTO> goodEntity = new HttpEntity<>(goodDocumentDTO);
        ResponseEntity<DocumentDTO> goodResponse = testRestTemplate.exchange(url, HttpMethod.POST, goodEntity, DocumentDTO.class);
        DocumentDTO documentDTOFromDB = mappingDocumentService.mapToDocumentDTO(documentService.findByCode(goodDocumentDTO.getCode()));
        assertEquals(goodDocumentDTO, goodResponse.getBody());
        assertEquals(goodDocumentDTO, documentDTOFromDB);
        assertEquals(HttpStatus.CREATED, goodResponse.getStatusCode());
    }

    @Test
    void update() {
        String url = "/documents/%d";
        List<Long> ids = template.query("SELECT * from documents", idMapper);
        Long goodId = ids.get(ids.size() / 2);
        DocumentDTO updatedDocumentDTO = new DocumentDTO("DocumentTest", "d00001Test");
        HttpEntity<DocumentDTO> goodEntity = new HttpEntity<>(updatedDocumentDTO);
        ResponseEntity<DocumentDTO> goodResponse = testRestTemplate.exchange(String.format(url, goodId), HttpMethod.PUT, goodEntity, DocumentDTO.class);
        assertEquals(updatedDocumentDTO, goodResponse.getBody());
        assertEquals(HttpStatus.OK, goodResponse.getStatusCode());
        assertEquals(goodId, documentService.findByCode(updatedDocumentDTO.getCode()).getId());
        Long badId = ids.get(ids.size() - 1) + 1;
        ResponseEntity<DocumentDTO> badResponse = testRestTemplate.exchange(String.format(url, badId), HttpMethod.PUT, goodEntity, DocumentDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }

    @Test
    void getDocumentsInBox() {
        String url = "/documents/box/%d";
        Long goodId = 2L;
        DocumentDTO documentDTO1 = new DocumentDTO("Document2", "d000002");
        DocumentDTO documentDTO2 = new DocumentDTO("Document6", "d000006");
        DocumentDTO notContainDocumentDTO = new DocumentDTO("Document3", "d000003");
        ParameterizedTypeReference<List<DocumentDTO>> typeRef = new ParameterizedTypeReference<List<DocumentDTO>>() {
        };
        ResponseEntity<List<DocumentDTO>> goodResponse = testRestTemplate.exchange(String.format(url, goodId), HttpMethod.GET, null, typeRef);
        assertEquals(HttpStatus.OK, goodResponse.getStatusCode());
        assertNotNull(goodResponse.getBody());
        assertTrue(goodResponse.getBody().contains(documentDTO1));
        assertTrue(goodResponse.getBody().contains(documentDTO2));
        assertFalse(goodResponse.getBody().contains(notContainDocumentDTO));
    }

    @Test
    void putDocumentInBox() {
        String url = "/documents/box/%d";
        String code = "EmptyBox1";
        Long boxId = 2L;
        Box box = boxService.findBoxById(boxId);
        Document document = documentService.findByCode(code);
        assertNull(document.getBox());
        DocumentDTO documentDTO = mappingDocumentService.mapToDocumentDTO(document);
        HttpEntity<DocumentDTO> goodEntity = new HttpEntity<>(documentDTO);
        ResponseEntity<DocumentDTO> goodResponse = testRestTemplate.exchange(String.format(url, boxId), HttpMethod.PUT, goodEntity, DocumentDTO.class);
        assertNotNull(goodResponse.getBody().getBoxDTO());
        assertEquals(box.getCode(), goodResponse.getBody().getBoxDTO().getCode());
    }

    @Test
    void extractDocumentFromBox() {
        String url = "/documents/%d";
        Long goodId = 2L;
        DocumentDTO deletedDocumentDTO = documentController.findDocumentByID(goodId);
        Long boxId = boxService.findByCode(deletedDocumentDTO.getBoxDTO().getCode()).getId();
        ResponseEntity<DocumentDTO> goodEntity = testRestTemplate.exchange(String.format(url, goodId), HttpMethod.DELETE, null, DocumentDTO.class);
        assertEquals(HttpStatus.OK, goodEntity.getStatusCode());
        List<DocumentDTO> documents = documentController.getDocumentsInBox(boxId);
        DocumentDTO deletedDocumentDTOFromDB = mappingDocumentService.mapToDocumentDTO(documentService.findByCode(deletedDocumentDTO.getCode()));
        assertFalse(documents.contains(deletedDocumentDTOFromDB));
    }

}