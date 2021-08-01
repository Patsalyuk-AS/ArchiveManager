package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import org.springframework.stereotype.Service;

@Service
public interface BoxService {

    Box getBoxByID(Long id);

    Box create(Box box);

    Box update(Long id, Box box);

    Box findByCode(String code);

}
