package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import com.github.patsalyukas.archivemanager.services.MappingDocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Long goodId = 5L;
        DocumentDTO oldDocumentDTO = mappingDocumentService.mapToDocumentDTO(documentService.getDocumentByID(goodId));
        DocumentDTO updatedDocumentDTO = new DocumentDTO("DocumentTest", "d00000Test");
        HttpEntity<DocumentDTO> goodEntity = new HttpEntity<>(updatedDocumentDTO);
        ResponseEntity<DocumentDTO> goodResponse = testRestTemplate.exchange(String.format(url, goodId), HttpMethod.PUT, goodEntity, DocumentDTO.class);
        assertEquals(updatedDocumentDTO, goodResponse.getBody());
        assertEquals(HttpStatus.OK, goodResponse.getStatusCode());
        assertEquals(goodId, documentService.findByCode(updatedDocumentDTO.getCode()).getId());
        Long badId = 20L;
        ResponseEntity<DocumentDTO> badResponse = testRestTemplate.exchange(String.format(url, badId), HttpMethod.PUT, goodEntity, DocumentDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }

    @Test
    void getDocumentsInBox() {

    }

    @Test
    void putDocumentInBox() {
        //TODO
    }

    @Test
    void extractDocumentFromBox() {
        //TODO
    }
}