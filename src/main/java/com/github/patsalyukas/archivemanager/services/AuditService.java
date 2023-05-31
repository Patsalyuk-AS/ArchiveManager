package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Audit;

import java.util.List;

public interface AuditService {

    List<Audit> getAudits();
}
