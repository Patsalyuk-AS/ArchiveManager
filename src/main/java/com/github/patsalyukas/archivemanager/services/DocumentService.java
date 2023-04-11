package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Document;

import java.util.List;

public interface DocumentService {

    Document findDocumentById(Long id);

    List<Document> getAllDocuments();

    Document create(Document document);

    Document update(Long id, Document document);

    List<Document> getDocumentsInBox(Long boxId);

    Document putDocumentInBox(Long boxId, Document document);

    Document extractDocumentFromBox(Long id);

    Document findByCode(String code);

}
