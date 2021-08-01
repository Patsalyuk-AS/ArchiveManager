package com.github.patsalyukas.archivemanager.repositories;

import com.github.patsalyukas.archivemanager.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document findByCode(String code);

}
