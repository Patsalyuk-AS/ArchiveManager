package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
        //TODO
    }

    @Test
    void update() {
        //TODO
    }

    @Test
    void getDocumentsInBox() {
        //TODO
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