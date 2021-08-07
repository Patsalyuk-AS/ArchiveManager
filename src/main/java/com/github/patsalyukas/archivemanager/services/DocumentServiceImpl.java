package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
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
    BoxService boxService;

    @Override
    public Document getDocumentByID(Long id) {
        return documentRepository.findById(id).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public Document create(Document document) {
        if (documentRepository.existsByCode(document.getCode())) {
            throw new DocumentExist();
        }
        return documentRepository.saveAndFlush(document);
    }

    @Override
    public Document update(Long id, Document document) {
        if (!documentRepository.existsById(id)) {
            throw new DocumentNotFoundException();
        }
        document.setId(id);
        return documentRepository.saveAndFlush(document);
    }

    @Override
    public List<Document> getDocumentsInBox(Long boxId) {
        Box box = boxService.getBoxByID(boxId);
        return documentRepository.findByBox(box).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public Document putDocumentInBox(Long boxId, Document document) {
        Box box = boxService.getBoxByID(boxId);
        Document documentFromDB = findByCode(document.getCode());
        document.setBox(box);
        return update(documentFromDB.getId(), document);
    }

    @Override
    public Document extractDocumentFromBox(Long id) {
        Document documentFromDB = getDocumentByID(id);
        documentFromDB.setBox(null);
        return update(id, documentFromDB);
    }

    @Override
    public Document findByCode(String code) {
        return documentRepository.findByCode(code).orElseThrow(DocumentNotFoundException::new);
    }

}
