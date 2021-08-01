package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Document;

import java.util.List;

public interface DocumentService {

    Document getDocumentByID(Long id);

    Document create(Document document);

    Document update(Long id, Document document);

    List<Document> getDocumentsInBox(Long id);

    boolean putDocumentInBox(Long id, Document document);

    Document extractDocumentFromBox(Long id);

    Document findByCode(String code);

}
