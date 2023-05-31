package com.github.patsalyukas.archivemanager.repositories;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findByCode(String code);

    boolean existsByCode(String code);

    Optional<List<Document>> findByBox(Box box);

}
