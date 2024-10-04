package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.TurnDTO;
import com.fdifrison.catan.core.entity.Turn;
import org.mapstruct.Mapper;

@Mapper
public interface TurnMapper {

    Turn toEntity(TurnDTO turnDTO);
}
