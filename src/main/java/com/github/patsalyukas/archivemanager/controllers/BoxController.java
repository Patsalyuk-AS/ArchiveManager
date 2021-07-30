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
        return new ResponseEntity<>(boxService.getBoxByCode(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> create(@RequestBody Box box) {
        boxService.create(box);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id) {
        boxService.update(id);
    }


}
