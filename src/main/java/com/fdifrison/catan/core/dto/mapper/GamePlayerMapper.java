package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.entity.GamePlayer;
import com.fdifrison.catan.core.entity.Player;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GamePlayerMapper {

    GamePlayer toEntity(GameSetupDTO.GamePlayerInfoDTO playerInfo);

    @Mapping(target = "gameId", source = "gameId")
    GameSetupDTO.GamePlayerInfoDTO updateDto(GameSetupDTO.GamePlayerInfoDTO playerInfo, Long gameId);

    default List<GameSetupDTO.GamePlayerInfoDTO> updateDto(
            List<GameSetupDTO.GamePlayerInfoDTO> playerInfos, Long gameId) {
        if (playerInfos == null) {
            return null;
        }
        return playerInfos.stream()
                .map(playerInfo -> updateDto(playerInfo, gameId))
                .collect(Collectors.toList());
    }

    GameDTO.GamePlayerDTO toDto(GamePlayer gamePlayer, Player player);
}
