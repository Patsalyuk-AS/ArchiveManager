package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.exceptions.BoxExist;
import com.github.patsalyukas.archivemanager.exceptions.BoxNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoxServiceImplTest {

    @Autowired
    BoxService boxService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Document> documentMapper = (((resultSet, rowNum) -> new Document(resultSet.getString("name"), resultSet.getString("code"))));
    private final RowMapper<Long> idMapper = ((resultSet, rowNum) -> resultSet.getLong("id"));
    private final RowMapper<Box> boxMapper = (((resultSet, rowNum) -> new Box(resultSet.getString("name"), resultSet.getString("code"))));

    @Test
    void getBoxByID() {
        jdbcTemplate.query("SELECT * FROM BOXES", idMapper).stream().limit(20).forEach(id -> {
            Box realBox = boxService.findBoxById(id);
            Box expectedBox = jdbcTemplate.queryForObject("SELECT * FROM Boxes WHERE id = ?", boxMapper, id);
            assertEquals(expectedBox, realBox);
        });
    }

    @Test
    void create() {
        String code = "t0001";
        Box box = new Box("Test", code);
        Box createdBox = boxService.create(box);
        assertEquals(box, createdBox);
        assertNotNull(boxService.findByCode(code));
        Box existBox = new Box("Test", code);
        assertThrows(BoxExist.class, () -> boxService.create(existBox));
    }

    @Test
    void update() {
        Random random = new Random();
        List<Long> ids = jdbcTemplate.query("SELECT * FROM BOXES", idMapper);
        Long id = ids.get(random.nextInt(ids.size()));
        Box box = new Box("Box3", "t0003");
        Box boxFromDB = jdbcTemplate.queryForObject("SELECT * FROM BOXES WHERE id = ?", boxMapper, id);
        assertEquals(boxFromDB, boxService.findBoxById(id));
        boxService.update(id, box);
        assertNotNull(boxService.findByCode("t0003"));
        assertEquals("t0003", boxService.findBoxById(id).getCode());
        Box notExistBox = new Box("Box3", "t0001");
        Long notExistId = ids.get(ids.size() - 1) + 20;
        assertThrows(BoxNotFoundException.class, () -> boxService.update(notExistId, notExistBox));
    }

    @Test
    void findByCode() {
        Box box = new Box("Box4", "b0004");
        assertEquals(box, boxService.findByCode("b0004"));
    }

    @Test
    void getDocumentsInBox() {
        Long id = getId();
        List<Document> expectedDocuments = jdbcTemplate.query("SELECT * FROM DOCUMENTS WHERE BOX = ?", documentMapper, id);
        Set<Document> documents = boxService.getDocumentsInBox(id);
        assertEquals(expectedDocuments.size(), documents.size());
        expectedDocuments.forEach(document -> assertTrue(documents.contains(document)));
    }

    private Long getId() {
        Random random = new Random();
        List<Long> ids = jdbcTemplate.query("SELECT * FROM BOXES", idMapper);
        return ids.get(random.nextInt(ids.size()));
    }

}