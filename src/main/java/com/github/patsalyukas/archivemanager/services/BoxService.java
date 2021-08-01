package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import org.springframework.stereotype.Service;

@Service
public interface BoxService {

    Box getBoxByCode(Long id);

    void create(Box box);

    boolean update(Long id);

}
