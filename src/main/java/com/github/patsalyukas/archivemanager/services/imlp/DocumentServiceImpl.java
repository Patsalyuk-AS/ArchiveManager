package com.github.patsalyukas.archivemanager.services.imlp;

import com.github.patsalyukas.archivemanager.annotation.LogExecutionTime;
import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.exceptions.DocumentExist;
import com.github.patsalyukas.archivemanager.exceptions.DocumentNotFoundException;
import com.github.patsalyukas.archivemanager.repositories.DocumentRepository;
import com.github.patsalyukas.archivemanager.services.BoxService;
import com.github.patsalyukas.archivemanager.services.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@LogExecutionTime
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final BoxService boxService;

    @Override
    public Document findDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public Document create(Document document) {
        if (documentRepository.existsByCode(document.getCode())) {
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
    public List<Document> getDocumentsInBox(Long boxId) {
        Box box = boxService.findBoxById(boxId);
        return documentRepository.findByBox(box).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public Document putDocumentInBox(Long boxId, Document document) {
        Box box = boxService.findBoxById(boxId);
        Document documentFromDB = findByCode(document.getCode());
        documentFromDB.setBox(box);
        return documentRepository.save(documentFromDB);
    }

    @Override
    public Document extractDocumentFromBox(Long id) {
        Document documentFromDB = findDocumentById(id);
        documentFromDB.setBox(null);
        return documentRepository.save(documentFromDB);
    }

    @Override
    public Document findByCode(String code) {
        return documentRepository.findByCode(code).orElseThrow(DocumentNotFoundException::new);
    }

}
