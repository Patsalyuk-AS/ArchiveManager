package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.exceptions.DocumentExist;
import com.github.patsalyukas.archivemanager.exceptions.DocumentNotFoundException;
import com.github.patsalyukas.archivemanager.repositories.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    DocumentRepository documentRepository;

    @Override
    public Document getDocumentByID(Long id) {
        return documentRepository.findById(id).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public Document create(Document document) {
        if (findByCode(document.getCode()) != null) {
            throw new DocumentExist();
        }
        return documentRepository.save(document);
    }

    @Override
    public Document update(Long id, Document document) {
        if (!documentRepository.existsById(id)) {
            throw new DocumentNotFoundException();
        }
        document.setId(id);
        return documentRepository.save(document);
    }

    @Override
    public List<Document> getDocumentsInBox(Long id) {
        //TODO
        return null;
    }

    @Override
    public boolean putDocumentInBox(Long id, Document document) {
        return false;
    }

    @Override
    public Document extractDocumentFromBox(Long id) {
        return null;
    }

    @Override
    public Document findByCode(String code) {
        return documentRepository.findByCode(code);
    }
}
