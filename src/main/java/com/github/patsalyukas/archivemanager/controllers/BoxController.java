package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.services.BoxService;
import com.github.patsalyukas.archivemanager.services.MappingBoxService;
import com.github.patsalyukas.archivemanager.services.MappingDocumentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boxes")
@AllArgsConstructor
public class BoxController {

    BoxService boxService;
    MappingBoxService mappingBoxService;
    MappingDocumentService mappingDocumentService;

    @GetMapping("/{id}")
    @Operation(summary = "Find box by ID")
    public BoxDTO findBoxById(@PathVariable Long id) {
        Box box = boxService.findBoxById(id);
        return (mappingBoxService.mapToBoxDTO(box));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create box")
    public BoxDTO create(@RequestBody BoxDTO boxDTO) {
        Box box = mappingBoxService.mapToBoxEntity(boxDTO);
        return mappingBoxService.mapToBoxDTO(boxService.create(box));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update box")
    public BoxDTO update(@PathVariable Long id, @RequestBody BoxDTO boxDTO) {
        Box box = boxService.update(id, mappingBoxService.mapToBoxEntity(boxDTO));
        return mappingBoxService.mapToBoxDTO(box);
    }

    @GetMapping("/documents/{boxId}")
    @Operation(summary = "Get documents in a box by box ID")
    public Set<DocumentDTO> getDocumentsInBox(@PathVariable Long boxId) {
        return boxService.getDocumentsInBox(boxId).stream().map(mappingDocumentService::mapToDocumentDTO).collect(Collectors.toSet());
    }

}
