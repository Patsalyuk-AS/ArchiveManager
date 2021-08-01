package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {

    DocumentService documentService;

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentByID(@PathVariable Long id) {
        return new ResponseEntity<>(documentService.getDocumentByID(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Document> create(@RequestBody Document document) {
        return new ResponseEntity<>(documentService.create(document), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> update(@PathVariable Long id, @RequestBody Document document) {
        return new ResponseEntity<>(documentService.update(id, document), HttpStatus.OK);
    }

    @GetMapping("/box/{boxId}")
    public ResponseEntity<List<Document>> getDocumentsInBox(@PathVariable Long boxId) {
        List<Document> documents = documentService.getDocumentsInBox(boxId);
        return (documents != null) ? new ResponseEntity<>(documents, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/box/{boxId}")
    public ResponseEntity<Document> putDocumentInBox(@PathVariable Long boxId, @RequestBody Document document) {
        return new ResponseEntity<>(documentService.putDocumentInBox(boxId, document), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Document> extractDocumentFromBox(@PathVariable Long id) {
        Document document = documentService.extractDocumentFromBox(id);
        return (document != null) ? new ResponseEntity<>(document, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
