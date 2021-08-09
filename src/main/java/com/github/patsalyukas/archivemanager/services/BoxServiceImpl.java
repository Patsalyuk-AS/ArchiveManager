package com.github.patsalyukas.archivemanager.services;

import com.github.patsalyukas.archivemanager.entities.Box;
import com.github.patsalyukas.archivemanager.exceptions.BoxExist;
import com.github.patsalyukas.archivemanager.exceptions.BoxNotFoundException;
import com.github.patsalyukas.archivemanager.repositories.BoxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoxServiceImpl implements BoxService {

    private BoxRepository boxRepository;

    @Override
    public Box findBoxByID(Long id) {
        return boxRepository.findById(id).orElseThrow(BoxNotFoundException::new);
    }

    @Override
    public Box create(Box box) {
        if (boxRepository.existsByCode(box.getCode())) {
            throw new BoxExist();
        }
        return boxRepository.save(box);
    }

    @Override
    public Box update(Long id, Box box) {
        if (!boxRepository.existsById(id)) {
            throw new BoxNotFoundException();
        }
        box.setId(id);
        return boxRepository.save(box);
    }

    @Override
    public Box findByCode(String code) {
        return boxRepository.findByCode(code).orElseThrow(BoxNotFoundException::new);
    }
}
