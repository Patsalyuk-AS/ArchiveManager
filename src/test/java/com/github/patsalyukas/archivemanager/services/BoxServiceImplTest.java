package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.exceptions.BoxExist;
import com.github.patsalyukas.archivemanager.exceptions.BoxNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class BoxServiceImplTest {

    @Autowired
    BoxService boxService;

    @Test
    void getBoxByID() {
        Box box = boxService.getBoxByID(3L);
        Box boxTest = new Box("Box3", "b0003");
        assertEquals(boxTest, box);
    }

    @Test
    void create() {
        Box box = new Box("Test", "t0001");
        Box createdBox = boxService.create(box);
        assertEquals(box, createdBox);
        assertNotNull(boxService.findByCode("t0001"));
        Box existBox = new Box("Test", "t0001");
        assertThrows(BoxExist.class, () -> boxService.create(existBox));
    }

    @Test
    void update() {
        Box box = new Box("Box3", "t0003");
        assertEquals("b0003", boxService.getBoxByID(3L).getCode());
        boxService.update(3L, box);
        assertNotNull(boxService.findByCode("t0003"));
        assertEquals("t0003", boxService.getBoxByID(3L).getCode());
        Box notExistBox = new Box("Box3", "t0001");
        assertThrows(BoxNotFoundException.class, () -> boxService.update(20L, notExistBox));
    }

    @Test
    void findByCode() {
        Box box = new Box("Box4", "b0004");
        assertEquals(box, boxService.findByCode("b0004"));
    }
}