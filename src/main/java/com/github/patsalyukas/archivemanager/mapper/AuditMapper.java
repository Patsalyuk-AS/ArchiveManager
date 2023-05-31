package com.github.patsalyukas.archivemanager.mapper;

import com.github.patsalyukas.archivemanager.dto.AuditDTO;
import com.github.patsalyukas.archivemanager.entities.Audit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AuditMapper {

    AuditDTO toAuditDTO(Audit audit);

    List<AuditDTO> toAuditDTOList(List<Audit> auditList);
}
