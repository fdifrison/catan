package com.fdifrison.catan.core.dto.mapper;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.entity.GamePlayer;
import com.fdifrison.catan.core.entity.Player;
import java.util.List;
import java.util.stream.Collectors;

import com.fdifrison.catan.core.entity.projection.GamePlayerStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface GamePlayerMapper {

    @Mapping(target = "plainScore", ignore = true)
    @Mapping(target = "victoryPointsDrawn", ignore = true)
    @Mapping(target = "winner", ignore = true)
    GamePlayer PlayerInfoToEntity(GameSetupDTO.GamePlayerInfoDTO playerInfo);

    @Mapping(target = "gameId", ignore = true)
    GamePlayer toEntity(GameDTO.GamePlayerDTO gamePlayerDTO);

    void updateEntity(@MappingTarget GamePlayer gamePlayer, GameDTO.GamePlayerDTO gamePlayerDTO);

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

    @Mapping(target = "developCardDrawn", ignore = true)
    @Mapping(target = "knightCardPlayed", ignore = true)
    @Mapping(target = "roadsBuilt", ignore = true)
    @Mapping(target = "coloniesBuilt", ignore = true)
    @Mapping(target = "citiesBuilt", ignore = true)
    @Mapping(target = "longestRoad", ignore = true)
    @Mapping(target = "largestArmy", ignore = true)
    GameDTO.GamePlayerDTO toDto(GamePlayer gamePlayer, Player player);

    @Mapping(target = "developCardDrawn", source = "statistics.developCardDrawn")
    @Mapping(target = "knightCardPlayed", source = "statistics.knightCardPlayed")
    @Mapping(target = "roadsBuilt", source = "statistics.roadsBuilt")
    @Mapping(target = "coloniesBuilt", source = "statistics.coloniesBuilt")
    @Mapping(target = "citiesBuilt", source = "statistics.citiesBuilt")
    @Mapping(target = "longestRoad", source = "statistics.longestRoad")
    @Mapping(target = "largestArmy", source = "statistics.largestArmy")
    GameDTO.GamePlayerDTO updateDtoWithStatistics(GameDTO.GamePlayerDTO dto, GamePlayerStatistics statistics);

    @Mapping(target = "plainScore", source = "score")
    GameDTO.GamePlayerDTO updateDtoWithScore(GameDTO.GamePlayerDTO dto, int score);


}
