package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.mapper.BoxMapper;
import com.github.patsalyukas.archivemanager.mapper.DocumentMapper;
import com.github.patsalyukas.archivemanager.services.BoxService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boxes")
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;
    private final BoxMapper boxMapper;
    private final DocumentMapper documentMapper;

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get all boxes")
    public List<BoxDTO> getAllBoxes() {
        return boxMapper.toDTOList(boxService.getAllBoxes());
    }

    @GetMapping(
            value = "/{id}",
            produces = "application/json"
    )
    @Operation(summary = "Find box by ID")
    public BoxDTO findBoxById(@PathVariable Long id) {
        return boxMapper.toDTO(boxService.findBoxById(id));
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create box")
    public BoxDTO create(@RequestBody BoxDTO boxDTO) {
        return boxMapper.toDTO(boxService.create(boxMapper.fromDTO(boxDTO)));
    }

    @PutMapping(
            value = "/{id}",
            produces = "application/json"
    )
    @Operation(summary = "Update box")
    public BoxDTO update(@PathVariable Long id, @RequestBody BoxDTO boxDTO) {
        return boxMapper.toDTO(boxService.update(id, boxMapper.fromDTO(boxDTO)));
    }

    @GetMapping(
            value = "/documents/{boxId}",
            produces = "application/json"
    )
    @Operation(summary = "Get documents in a box by box ID")
    public Set<DocumentDTO> getDocumentsInBox(@PathVariable Long boxId) {
        return boxService.getDocumentsInBox(boxId).stream().map(documentMapper::toDTO).collect(Collectors.toSet());
    }

}
