package com.github.patsalyukas.archivemanager.mapper;

import com.github.patsalyukas.archivemanager.dto.DocumentDTO;
import com.github.patsalyukas.archivemanager.entities.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {BoxMapper.class})
public interface DocumentMapper {

    @Mapping(source = "box", target = "boxDTO")
    DocumentDTO toDTO(Document document);

    List<DocumentDTO> toDTOList(List<Document> documentList);

    @Mapping(source = "boxDTO", target = "box")
    Document fromDTO(DocumentDTO documentDTO);
}
