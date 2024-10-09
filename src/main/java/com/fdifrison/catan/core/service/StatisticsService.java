package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.PlayerStatisticsDTO;
import com.fdifrison.catan.core.dto.mapper.GamePlayerMapper;
import com.fdifrison.catan.core.entity.projection.PlayerDiceRollsCount;
import com.fdifrison.catan.core.repository.TurnRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final TurnRepository turnRepository;
    private final GamePlayerMapper gamePlayerMapper;

    public StatisticsService(TurnRepository turnRepository, GamePlayerMapper gamePlayerMapper) {
        this.turnRepository = turnRepository;
        this.gamePlayerMapper = gamePlayerMapper;
    }

    public List<PlayerStatisticsDTO> getGameDiceDashboard(long gameId) {
        var playerStatisticsDTOS = turnRepository.findDiceCountByGameId(gameId).stream()
                .collect(Collectors.groupingBy(
                        PlayerDiceRollsCount::playerId,
                        Collectors.collectingAndThen(
                                Collectors.toMap(PlayerDiceRollsCount::outcome, PlayerDiceRollsCount::count), map -> {
                                    return new PlayerStatisticsDTO.DiceStatisticsDTO(
                                            map.keySet(),
                                            map.values().stream()
                                                    .map(Long::doubleValue)
                                                    .toList());
                                })))
                .entrySet()
                .stream()
                .map(k -> {
                    return new PlayerStatisticsDTO(k.getKey(), k.getValue());
                })
                .collect(Collectors.toList());
        var probabilisticDistribution = computeProbabilisticDistribution(gameId);
        var distribution = new PlayerStatisticsDTO(0, probabilisticDistribution);
        playerStatisticsDTOS.add(distribution);
        return playerStatisticsDTOS;
    }

    private PlayerStatisticsDTO.DiceStatisticsDTO computeProbabilisticDistribution(long gameId) {
        long turnNumber = turnRepository.countByGameId(gameId);
        var dices = Set.of(2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L);
        var probability = List.of(
                1 / 36D, 2 / 36D, 3 / 36D, 4 / 36D, 5 / 36D, 6 / 36D, 5 / 36D, 4 / 36D, 3 / 36D, 2 / 36D, 1 / 36D);
        return new PlayerStatisticsDTO.DiceStatisticsDTO(
                dices, probability.stream().map(i -> i * turnNumber).toList());
    }

    public Map<Long, Long> getPlayerOverallDiceDashboard(long playerId) {
        return turnRepository.findOverallDiceCountByPlayerId(playerId).stream()
                .collect(Collectors.toMap(PlayerDiceRollsCount::outcome, PlayerDiceRollsCount::count));
    }

    public List<GameDTO.GamePlayerDTO> computeGamePlayerStatistics(
            long gameId, List<GameDTO.GamePlayerDTO> gamePlayers) {
        var statistics = turnRepository.computeGamePlayerStatistics(gameId);
        return StreamUtils.zip(
                        gamePlayers.stream().sorted(), statistics.stream(), gamePlayerMapper::updateDtoWithStatistics)
                .map(this::computePlayerScore)
                .toList();
    }

    private GameDTO.GamePlayerDTO computePlayerScore(GameDTO.GamePlayerDTO player) {
        var score = player.citiesBuilt() * 2 + (player.coloniesBuilt() - player.citiesBuilt());
        int scoreWithBonus = player.longestRoad() || player.largestArmy() ? score + 2 : score;
        return gamePlayerMapper.updateDtoWithScore(player, scoreWithBonus);
    }

    // remove the positioning initial turns from count
    public long countTurns(long gameId, int numOfPlayers) {
        return turnRepository.countByGameId(gameId) - numOfPlayers;
    }
}
