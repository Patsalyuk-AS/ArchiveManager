package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Document;
import org.springframework.stereotype.Service;

@Service
public class MappingDocumentServiceImpl implements MappingDocumentService {

    MappingBoxService mappingBoxService;

    @Override
    public Document mapToDocumentEntity(DocumentDTO documentDTO) {
        Document document = new Document(documentDTO.getName(), documentDTO.getCode());
        if (documentDTO.getBoxDTO() != null) {
            document.setBox(mappingBoxService.mapToBoxEntity(documentDTO.getBoxDTO()));
        }
        return document;
    }

    @Override
    public DocumentDTO mapToDocumentDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO(document.getName(), document.getCode());
        if (document.getBox() != null) {
            documentDTO.setBoxDTO(mappingBoxService.mapToBoxDTO(document.getBox()));
        }
        return documentDTO;
    }
}
