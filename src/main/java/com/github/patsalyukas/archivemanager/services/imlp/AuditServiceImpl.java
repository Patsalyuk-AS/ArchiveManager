package com.github.patsalyukas.archivemanager.services.imlp;

import com.github.patsalyukas.archivemanager.entities.Audit;
import com.github.patsalyukas.archivemanager.repositories.AuditRepository;
import com.github.patsalyukas.archivemanager.services.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public List<Audit> getAudits() {
        return auditRepository.findAll();
    }
}
