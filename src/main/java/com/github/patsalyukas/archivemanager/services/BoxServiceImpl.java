package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.exceptions.BoxNotFoundException;
import com.github.patsalyukas.archivemanager.repositories.BoxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoxServiceImpl implements BoxService {

    private BoxRepository boxRepository;

    @Override
    public Box getBoxByCode(Long id) {
        return boxRepository.findById(id).orElseThrow(BoxNotFoundException::new);
    }

    @Override
    public void create(Box box) {
        //TODO
    }

    @Override
    public boolean update(Long id) {
        //TODO
        return true;
    }

}
