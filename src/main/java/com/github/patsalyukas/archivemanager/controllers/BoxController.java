package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.services.BoxService;
import com.github.patsalyukas.archivemanager.services.MappingBoxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boxes")
@AllArgsConstructor
public class BoxController {

    BoxService boxService;
    MappingBoxService mappingBoxService;

    @GetMapping("/{id}")
    public ResponseEntity<BoxDTO> getBoxByCode(@PathVariable Long id) {
        Box box = boxService.getBoxByID(id);
        return new ResponseEntity<>(mappingBoxService.mapToBoxDTO(box), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BoxDTO> create(@RequestBody BoxDTO boxDTO) {
        Box box = mappingBoxService.mapToBoxEntity(boxDTO);
        return new ResponseEntity<>(mappingBoxService.mapToBoxDTO(boxService.create(box)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoxDTO> update(@PathVariable Long id, @RequestBody BoxDTO boxDTO) {
        Box box = boxService.update(id, mappingBoxService.mapToBoxEntity(boxDTO));
        return new ResponseEntity<>(mappingBoxService.mapToBoxDTO(box), HttpStatus.OK);
    }

}
