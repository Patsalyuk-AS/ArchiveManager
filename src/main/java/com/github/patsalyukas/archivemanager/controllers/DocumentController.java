package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import com.github.patsalyukas.archivemanager.services.MappingDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DocumentDTO> getDocumentByID(@PathVariable Long id) {
        Document document = documentService.getDocumentByID(id);
        return new ResponseEntity<>(mappingDocumentService.mapToDocumentDTO(document), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<DocumentDTO> create(@RequestBody DocumentDTO documentDTO) {
        Document document = documentService.create(mappingDocumentService.mapToDocumentEntity(documentDTO));
        return new ResponseEntity<>(mappingDocumentService.mapToDocumentDTO(document), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> update(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        Document document = documentService.update(id, mappingDocumentService.mapToDocumentEntity(documentDTO));
        return new ResponseEntity<>(mappingDocumentService.mapToDocumentDTO(document), HttpStatus.OK);
    }

    @GetMapping("/box/{boxId}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsInBox(@PathVariable Long boxId) {
        List<Document> documents = documentService.getDocumentsInBox(boxId);
        if (documents == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DocumentDTO> documentDTOList = documents.stream().map(mappingDocumentService::mapToDocumentDTO).collect(Collectors.toList());
        return new ResponseEntity<>(documentDTOList, HttpStatus.OK);
    }

    @PutMapping("/box/{boxId}")
    public ResponseEntity<DocumentDTO> putDocumentInBox(@PathVariable Long boxId, @RequestBody DocumentDTO documentDTO) {
        Document document = documentService.putDocumentInBox(boxId, mappingDocumentService.mapToDocumentEntity(documentDTO));
        return new ResponseEntity<>(mappingDocumentService.mapToDocumentDTO(document), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DocumentDTO> extractDocumentFromBox(@PathVariable Long id) {
        Document document = documentService.extractDocumentFromBox(id);
        if (document == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mappingDocumentService.mapToDocumentDTO(document), HttpStatus.OK);
    }

}
