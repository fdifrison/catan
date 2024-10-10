package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.GamePlayerStatisticsDTO;
import com.fdifrison.catan.core.entity.TurnView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TurnViewMapper {

    @Mapping(target = "longestRoad", ignore = true)
    @Mapping(target = "largestArmy", ignore = true)
    GamePlayerStatisticsDTO toGamePlayerStatisticsDTO(TurnView turnView);
}
