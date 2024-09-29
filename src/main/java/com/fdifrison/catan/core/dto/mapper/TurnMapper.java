package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.EndTurnDTO;
import com.fdifrison.catan.core.entity.Turn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TurnMapper {

    TurnMapper INSTANCE = Mappers.getMapper(TurnMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player", ignore = true)
    @Mapping(target = "game", ignore = true)
    @Mapping(target = "startTimestamp", ignore = true)
    @Mapping(target = "endTimestamp", ignore = true)
    @Mapping(target = "outcome", ignore = true)
    void updateEntity(@MappingTarget Turn turn, EndTurnDTO endTurnDTO);
}
