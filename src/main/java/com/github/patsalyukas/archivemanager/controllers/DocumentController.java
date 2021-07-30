package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {

    DocumentService documentService;

    @GetMapping("/{id}")
    public Document getDocumentByCode(@PathVariable Long id) {
        return documentService.getDocumentByCode(id);
    }

}
