package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTimestamp", ignore = true)
    @Mapping(target = "endTimestamp", ignore = true)
    @Mapping(target = "turns", ignore = true)
    @Mapping(target = "numberOfPlayers", ignore = true)
    Game initEntity(GameSetupDTO gameSetup);
}
