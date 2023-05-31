package com.github.patsalyukas.archivemanager.repositories;

import com.github.patsalyukas.archivemanager.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
}
