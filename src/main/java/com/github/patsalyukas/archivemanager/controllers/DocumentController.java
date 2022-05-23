package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import com.github.patsalyukas.archivemanager.services.MappingDocumentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {

    DocumentService documentService;
    MappingDocumentService mappingDocumentService;

    @GetMapping("/{id}")
    @Operation(summary = "Find document by ID")
    public DocumentDTO findDocumentByID(@PathVariable Long id) {
        Document document = documentService.findDocumentById(id);
        return mappingDocumentService.mapToDocumentDTO(document);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create document")
    public DocumentDTO create(@RequestBody DocumentDTO documentDTO) {
        Document document = documentService.create(mappingDocumentService.mapToDocumentEntity(documentDTO));
        return mappingDocumentService.mapToDocumentDTO(document);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update document")
    public DocumentDTO update(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        Document document = documentService.update(id, mappingDocumentService.mapToDocumentEntity(documentDTO));
        return mappingDocumentService.mapToDocumentDTO(document);
    }

    @GetMapping("/box/{boxId}")
    @Operation(summary = "Get documents in a box by box ID")
    public List<DocumentDTO> getDocumentsInBox(@PathVariable Long boxId) {
        List<Document> documents = documentService.getDocumentsInBox(boxId);
        return documents.stream().map(mappingDocumentService::mapToDocumentDTO).collect(Collectors.toList());
    }

    @PutMapping("/box/{boxId}")
    @Operation(summary = "Put documents in a box by box ID")
    public DocumentDTO putDocumentInBox(@PathVariable Long boxId, @RequestBody DocumentDTO documentDTO) {
        Document document = documentService.putDocumentInBox(boxId, mappingDocumentService.mapToDocumentEntity(documentDTO));
        return mappingDocumentService.mapToDocumentDTO(document);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Extract documents out of a box by box ID")
    public DocumentDTO extractDocumentFromBox(@PathVariable Long id) {
        Document document = documentService.extractDocumentFromBox(id);
        return mappingDocumentService.mapToDocumentDTO(document);
    }

}
