package com.github.patsalyukas.archivemanager.mapper;

import com.github.patsalyukas.archivemanager.dto.BoxDTO;
import com.github.patsalyukas.archivemanager.entities.Box;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BoxMapper {

    BoxDTO toDTO(Box box);

    List<BoxDTO> toDTOList(List<Box> boxList);

    Box fromDTO(BoxDTO boxDTO);
}
