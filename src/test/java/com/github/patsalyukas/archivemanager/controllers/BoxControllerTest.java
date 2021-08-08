package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.services.BoxService;
import com.github.patsalyukas.archivemanager.services.MappingBoxService;
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
class BoxControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    BoxService boxService;

    @Autowired
    MappingBoxService mappingBoxService;


    @Test
    void getBoxById() {
        BoxDTO boxDTO = new BoxDTO("Box3", "b0003");
        Long goodId = 3L;
        Long badId = 10L;
        String url = "/boxes/%d";
        ResponseEntity<BoxDTO> entityFound = testRestTemplate.getForEntity(String.format(url, goodId), BoxDTO.class);
        ResponseEntity<BoxDTO> entityNotFound = testRestTemplate.getForEntity(String.format(url, badId), BoxDTO.class);
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
        ResponseEntity<BoxDTO> goodResponse = testRestTemplate.exchange(String.format(url, goodId), HttpMethod.PUT, goodEntity, BoxDTO.class);
        assertEquals(HttpStatus.OK, goodResponse.getStatusCode());
        assertEquals(updatedBox, goodResponse.getBody());
        Long badId = 20L;
        ResponseEntity<BoxDTO> badResponse = testRestTemplate.exchange(String.format(url, badId), HttpMethod.PUT, goodResponse, BoxDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, badResponse.getStatusCode());
    }
}