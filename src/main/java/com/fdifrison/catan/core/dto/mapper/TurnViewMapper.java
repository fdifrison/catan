package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.GamePlayerStatisticsDTO;
import com.fdifrison.catan.core.entity.TurnView;
import org.mapstruct.Mapper;

@Mapper
public interface TurnViewMapper {

    GamePlayerStatisticsDTO toGamePlayerStatisticsDTO(TurnView turnView);

}
