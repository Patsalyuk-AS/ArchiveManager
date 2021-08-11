package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.services.BoxService;
import com.github.patsalyukas.archivemanager.services.MappingBoxService;
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
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ExtendWith(SpringExtension.class)
class BoxControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    BoxService boxService;

    @Autowired
    MappingBoxService mappingBoxService;

    private final RowMapper<Long> idMapper = ((resultSet, rowNum) -> resultSet.getLong("id"));
    private final RowMapper<Document> documentMapper = (((resultSet, rowNum) -> new Document(resultSet.getString("name"), resultSet.getString("code"))));
    @Autowired
    MappingDocumentService mappingDocumentService;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void getBoxById() {
        BoxDTO boxDTO = new BoxDTO("Box3", "b0003");
        Long goodId = 3L;
        Long badId = 10L;
        String url = "/boxes/%d";
        ResponseEntity<BoxDTO> entityFound = testRestTemplate.getForEntity(java.lang.String.format(url, goodId), BoxDTO.class);
        ResponseEntity<BoxDTO> entityNotFound = testRestTemplate.getForEntity(java.lang.String.format(url, badId), BoxDTO.class);
        assertEquals(boxDTO, entityFound.getBody());
        assertEquals(HttpStatus.OK, entityFound.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, entityNotFound.getStatusCode());
    }

    @Test
    void create() {
        BoxDTO goodBoxDTO = new BoxDTO("test", "t0001");
        String url = "/boxes/";
        HttpEntity<BoxDTO> goodEntity = new HttpEntity<>(goodBoxDTO);
        ResponseEntity<BoxDTO> goodResponse = testRestTemplate.exchange(url, HttpMethod.POST, goodEntity, BoxDTO.class);
        assertEquals(HttpStatus.CREATED, goodResponse.getStatusCode());
        assertEquals(goodBoxDTO, goodResponse.getBody());
        assertEquals(goodBoxDTO, mappingBoxService.mapToBoxDTO(boxService.findByCode(goodBoxDTO.getCode())));
    }

    @Test
    void update() {
        Long goodId = 2L;
        String url = "/boxes/%d";
        BoxDTO updatedBox = new BoxDTO("Box2Test", "t0002");
        HttpEntity<BoxDTO> goodEntity = new HttpEntity<>(updatedBox);
        ResponseEntity<BoxDTO> goodResponse = testRestTemplate.exchange(java.lang.String.format(url, goodId), HttpMethod.PUT, goodEntity, BoxDTO.class);
        assertEquals(HttpStatus.OK, goodResponse.getStatusCode());
        assertEquals(updatedBox, goodResponse.getBody());
        Long badId = 20L;
        ResponseEntity<BoxDTO> badResponse = testRestTemplate.exchange(java.lang.String.format(url, badId), HttpMethod.PUT, goodResponse, BoxDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }

    @Test
    void getDocumentsInBox() {
        String url = "/boxes/documents/%d";
        Random random = new Random();
        List<Long> ids = jdbcTemplate.query("SELECT * FROM BOXES", idMapper);
        Long id = ids.get(random.nextInt(ids.size()));
        List<DocumentDTO> expectedDocuments;
        do {
            expectedDocuments = jdbcTemplate.query("SELECT * FROM DOCUMENTS WHERE BOX = ?", documentMapper, id)
                    .stream()
                    .map(mappingDocumentService::mapToDocumentDTO)
                    .collect(Collectors.toList());
            System.out.println("----------------------------------------------------------");
            System.out.println("Size: " + expectedDocuments.size());
        } while (expectedDocuments.size() == 0);
        ParameterizedTypeReference<Set<DocumentDTO>> typeRef = new ParameterizedTypeReference<Set<DocumentDTO>>() {
        };
        ResponseEntity<Set<DocumentDTO>> response = testRestTemplate.exchange(String.format(url, id), HttpMethod.GET, null, typeRef);
        assertEquals(expectedDocuments.size(), Objects.requireNonNull(response.getBody()).size());
        expectedDocuments.forEach(document -> assertTrue(response.getBody().contains(document)));
        System.out.println("ID: " + id);
        System.out.println("Documents: " + expectedDocuments);
    }
}