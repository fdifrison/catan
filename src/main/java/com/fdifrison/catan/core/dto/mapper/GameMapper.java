package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GameMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTimestamp", ignore = true)
    @Mapping(target = "endTimestamp", ignore = true)
    @Mapping(target = "turns", ignore = true)
    Game initEntity(GameSetupDTO.GameInfoDTO gameInfo);

    GameDTO toDto(Game game);
}
