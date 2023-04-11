package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.mapper.DocumentMapper;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    @GetMapping(
            value = "/{id}",
            produces = "application/json"
    )
    @Operation(summary = "Find document by ID")
    public DocumentDTO findDocumentByID(@PathVariable Long id) {
        return documentMapper.toDTO(documentService.findDocumentById(id));
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get all documents")
    public List<DocumentDTO> getAllDocuments() {
        return documentMapper.toDTOList(documentService.getAllDocuments());
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create document")
    public DocumentDTO create(@RequestBody DocumentDTO documentDTO) {
        return documentMapper.toDTO(documentService.create(documentMapper.fromDTO(documentDTO)));
    }

    @PutMapping(
            value = "/{id}",
            produces = "application/json"
    )
    @Operation(summary = "Update document")
    public DocumentDTO update(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        return documentMapper.toDTO(documentService.update(id, documentMapper.fromDTO(documentDTO)));
    }

    @PutMapping(
            value = "/box/{boxId}",
            produces = "application/json"
    )
    @Operation(summary = "Put documents in a box by box ID")
    public DocumentDTO putDocumentInBox(@PathVariable Long boxId, @RequestBody DocumentDTO documentDTO) {
        return documentMapper.toDTO(documentService.putDocumentInBox(boxId, documentMapper.fromDTO(documentDTO)));
    }

    @DeleteMapping(
            value = "/{id}",
            produces = "application/json"
    )
    @Operation(summary = "Extract documents out of a box by box ID")
    public DocumentDTO extractDocumentFromBox(@PathVariable Long id) {
        return documentMapper.toDTO(documentService.extractDocumentFromBox(id));
    }
}
