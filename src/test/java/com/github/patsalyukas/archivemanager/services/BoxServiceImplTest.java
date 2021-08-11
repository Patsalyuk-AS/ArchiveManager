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

    private final RowMapper<Document> documentMapper = (((resultSet, rowNum) -> new Document(resultSet.getString("name"), resultSet.getString("code"))));

    private final RowMapper<Long> idMapper = ((resultSet, rowNum) -> resultSet.getLong("id"));
    private final RowMapper<Box> boxMapper = (((resultSet, rowNum) -> new Box(resultSet.getString("name"), resultSet.getString("code"))));
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void getBoxByID() {
        jdbcTemplate.query("SELECT * FROM BOXES", idMapper).stream().limit(20).forEach(id -> {
            Box realBox = boxService.findBoxByID(id);
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
        Box box = new Box("Box3", "t0003");
        assertEquals("b0003", boxService.findBoxByID(3L).getCode());
        boxService.update(3L, box);
        assertNotNull(boxService.findByCode("t0003"));
        assertEquals("t0003", boxService.findBoxByID(3L).getCode());
        Box notExistBox = new Box("Box3", "t0001");
        assertThrows(BoxNotFoundException.class, () -> boxService.update(20L, notExistBox));
    }

    @Test
    void findByCode() {
        Box box = new Box("Box4", "b0004");
        assertEquals(box, boxService.findByCode("b0004"));
    }

    @Test
    void getDocumentsInBox() {
        Random random = new Random();
        List<Long> ids = jdbcTemplate.query("SELECT * FROM BOXES", idMapper);
        Long id = ids.get(random.nextInt(ids.size()));
        List<Document> expectedDocuments = jdbcTemplate.query("SELECT * FROM DOCUMENTS WHERE BOX = ?", documentMapper, id);
        Set<Document> documents = boxService.getDocumentsInBox(id);
        assertEquals(expectedDocuments.size(), documents.size());
        expectedDocuments.forEach(document -> assertTrue(documents.contains(document)));
    }
}