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
    public ResponseEntity<HttpStatus> update(@PathVariable Long id, @RequestBody Document document) {
        return documentService.update(id, document) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Document>> getDocumentsInBox(@RequestParam Long id) {
        List<Document> documents = documentService.getDocumentsInBox(id);
        return (documents != null) ? new ResponseEntity<>(documents, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<HttpStatus> putDocumentInBox(@PathVariable Long id, @RequestBody Document document) {
        return documentService.putDocumentInBox(id, document) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Document> extractDocumentFromBox(@PathVariable Long id) {
        Document document = documentService.extractDocumentFromBox(id);
        return (document != null) ? new ResponseEntity<>(document, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
