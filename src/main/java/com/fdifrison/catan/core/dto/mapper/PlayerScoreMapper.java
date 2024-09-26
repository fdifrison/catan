package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.PlayerScoreDTO;
import com.fdifrison.catan.core.entity.PlayerScore;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerScoreMapper {

    PlayerScoreMapper INSTANCE = Mappers.getMapper(PlayerScoreMapper.class);

    PlayerScore toEntity(PlayerScoreDTO player);

    PlayerScoreDTO toDto(PlayerScore player);
}
