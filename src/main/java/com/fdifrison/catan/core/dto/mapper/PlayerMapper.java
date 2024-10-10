package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    Player toEntity(PlayerDTO playerDTO);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Player player, PlayerDTO playerDTO);

    PlayerDTO toDto(Player player);
}
