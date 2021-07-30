package com.github.patsalyukas.archivemanager.repositories;

import com.github.patsalyukas.archivemanager.entities.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {

}
