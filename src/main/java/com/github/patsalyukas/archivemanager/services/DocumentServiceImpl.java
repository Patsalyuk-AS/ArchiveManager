package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.exceptions.DocumentNotFoundException;
import com.github.patsalyukas.archivemanager.repositories.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    DocumentRepository documentRepository;

    @Override
    public Document getDocumentByCode(Long id) {
        return documentRepository.findById(id).orElseThrow(DocumentNotFoundException::new);
    }
}
