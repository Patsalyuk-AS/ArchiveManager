package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.services.BoxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boxes")
@AllArgsConstructor
public class BoxController {

    BoxService boxService;

    @GetMapping("/{id}")
    public ResponseEntity<Box> getBoxByCode(@PathVariable Long id) {
        return new ResponseEntity<>(boxService.getBoxByID(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Box> create(@RequestBody Box box) {
        return new ResponseEntity<>(boxService.create(box), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Box> update(@PathVariable Long id, @RequestBody Box box) {
        return new ResponseEntity<>(boxService.update(id, box), HttpStatus.OK);
    }

}
