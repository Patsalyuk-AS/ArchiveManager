package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.services.BoxService;
import com.github.patsalyukas.archivemanager.services.MappingBoxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boxes")
@AllArgsConstructor
public class BoxController {

    BoxService boxService;
    MappingBoxService mappingBoxService;

    @GetMapping("/{id}")
    public BoxDTO findBoxById(@PathVariable Long id) {
        Box box = boxService.findBoxByID(id);
        return (mappingBoxService.mapToBoxDTO(box));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public BoxDTO create(@RequestBody BoxDTO boxDTO) {
        Box box = mappingBoxService.mapToBoxEntity(boxDTO);
        return mappingBoxService.mapToBoxDTO(boxService.create(box));
    }

    @PutMapping("/{id}")
    public BoxDTO update(@PathVariable Long id, @RequestBody BoxDTO boxDTO) {
        Box box = boxService.update(id, mappingBoxService.mapToBoxEntity(boxDTO));
        return mappingBoxService.mapToBoxDTO(box);
    }

}
