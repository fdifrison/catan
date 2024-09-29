package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.EndTurnDTO;
import com.fdifrison.catan.core.dto.InitTurnDTO;
import com.fdifrison.catan.core.entity.Turn;
import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TurnMapper {

    TurnMapper INSTANCE = Mappers.getMapper(TurnMapper.class);

    void updateEntity(@MappingTarget Turn turn, EndTurnDTO endTurnDTO);

}
