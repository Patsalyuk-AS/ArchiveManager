package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Document;

public interface DocumentService {

    Document getDocumentByCode(Long id);

}
