package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import com.github.patsalyukas.archivemanager.services.MappingDocumentService;
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
    public DocumentDTO findDocumentByID(@PathVariable Long id) {
        Document document = documentService.findDocumentByID(id);
        return mappingDocumentService.mapToDocumentDTO(document);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentDTO create(@RequestBody DocumentDTO documentDTO) {
        Document document = documentService.create(mappingDocumentService.mapToDocumentEntity(documentDTO));
        return mappingDocumentService.mapToDocumentDTO(document);
    }

    @PutMapping("/{id}")
    public DocumentDTO update(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        Document document = documentService.update(id, mappingDocumentService.mapToDocumentEntity(documentDTO));
        return mappingDocumentService.mapToDocumentDTO(document);
    }

    @GetMapping("/box/{boxId}")
    public List<DocumentDTO> getDocumentsInBox(@PathVariable Long boxId) {
        List<Document> documents = documentService.getDocumentsInBox(boxId);
        return documents.stream().map(mappingDocumentService::mapToDocumentDTO).collect(Collectors.toList());
    }

    @PutMapping("/box/{boxId}")
    public DocumentDTO putDocumentInBox(@PathVariable Long boxId, @RequestBody DocumentDTO documentDTO) {
        Document document = documentService.putDocumentInBox(boxId, mappingDocumentService.mapToDocumentEntity(documentDTO));
        return mappingDocumentService.mapToDocumentDTO(document);
    }

    @DeleteMapping("/{id}")
    public DocumentDTO extractDocumentFromBox(@PathVariable Long id) {
        Document document = documentService.extractDocumentFromBox(id);
        return mappingDocumentService.mapToDocumentDTO(document);
    }

}
