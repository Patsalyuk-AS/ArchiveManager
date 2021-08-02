package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Document;

public interface MappingDocumentService {

    Document mapToDocumentEntity(DocumentDTO documentDTODTO);

    DocumentDTO mapToDocumentDTO(Document document);

}
