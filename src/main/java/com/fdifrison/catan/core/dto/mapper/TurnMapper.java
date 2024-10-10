package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.TurnDTO;
import com.fdifrison.catan.core.entity.Turn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TurnMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "game", ignore = true)
    @Mapping(target = "player", ignore = true)
    @Mapping(target = "startTimestamp", ignore = true)
    Turn toEntity(TurnDTO turnDTO);
}
