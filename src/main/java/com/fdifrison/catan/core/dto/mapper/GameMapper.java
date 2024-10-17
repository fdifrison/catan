package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = GamePlayerMapper.class)
public interface GameMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startTimestamp", ignore = true)
    @Mapping(target = "endTimestamp", ignore = true)
    @Mapping(target = "turns", ignore = true)
    @Mapping(target = "gamePlayers", ignore = true)
    Game initEntity(GameSetupDTO.SetupDTO gameInfo);

    @Mapping(target = "turnNumber", source = "turnNumber")
    GameDTO.GameInfoDTO toDto(Game game, long turnNumber);
}
