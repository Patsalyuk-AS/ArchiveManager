package com.github.patsalyukas.archivemanager.controllers;

import com.github.patsalyukas.archivemanager.dto.AuditDTO;
import com.github.patsalyukas.archivemanager.mapper.AuditMapper;
import com.github.patsalyukas.archivemanager.services.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;
    private final AuditMapper auditMapper;

    @GetMapping
    public ResponseEntity<List<AuditDTO>> getAudits() {
        return ResponseEntity.ok(auditMapper.toAuditDTOList(auditService.getAudits()));
    }
}
