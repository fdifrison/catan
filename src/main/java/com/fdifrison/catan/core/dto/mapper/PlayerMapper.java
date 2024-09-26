package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    Player toEntity(PlayerDTO playerDTO);
}
