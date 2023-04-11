package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.entities.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface BoxService {

    List<Box> getAllBoxes();

    Box findBoxById(Long id);

    Box create(Box box);

    Box update(Long id, Box box);

    Box findByCode(String code);

    Set<Document> getDocumentsInBox(Long boxId);

}
