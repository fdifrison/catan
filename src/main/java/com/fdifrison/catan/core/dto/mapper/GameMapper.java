package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    GameDTO toDto(Game game);
}
