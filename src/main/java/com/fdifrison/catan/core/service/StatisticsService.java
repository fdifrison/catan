package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GamePlayerStatisticsDTO;
import com.fdifrison.catan.core.dto.PlayerStatisticsDTO;
import com.fdifrison.catan.core.dto.mapper.GamePlayerMapper;
import com.fdifrison.catan.core.dto.mapper.TurnViewMapper;
import com.fdifrison.catan.core.entity.TurnView;
import com.fdifrison.catan.core.entity.projection.GamePlayerStatistics;
import com.fdifrison.catan.core.entity.projection.PlayerDiceRollsCount;
import com.fdifrison.catan.core.repository.GameRepository;
import com.fdifrison.catan.core.repository.TurnRepository;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;
import java.util.stream.Collectors;

import com.fdifrison.catan.core.repository.TurnViewRepository;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {


    private final TurnViewRepository turnViewRepository;
    private final GamePlayerMapper gamePlayerMapper;
    private final TurnService turnService;
    private final TurnViewMapper turnViewMapper;

    public StatisticsService(TurnRepository turnRepository, TurnViewRepository turnViewRepository, GamePlayerMapper gamePlayerMapper, TurnService turnService, TurnViewMapper turnViewMapper) {
        this.turnViewRepository = turnViewRepository;
        this.gamePlayerMapper = gamePlayerMapper;
        this.turnService = turnService;
        this.turnViewMapper = turnViewMapper;
    }

    public List<PlayerStatisticsDTO> getGameDiceDashboard(long gameId) {
        var playerStatisticsDTOS = turnService.findDiceCountByGameId(gameId).stream()
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
        long turnNumber = turnService.countByGameId(gameId);
        var dices = Set.of(2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L);
        var probability = List.of(
                1 / 36D, 2 / 36D, 3 / 36D, 4 / 36D, 5 / 36D, 6 / 36D, 5 / 36D, 4 / 36D, 3 / 36D, 2 / 36D, 1 / 36D);
        return new PlayerStatisticsDTO.DiceStatisticsDTO(
                dices, probability.stream().map(i -> i * turnNumber).toList());
    }

    public Map<Long, Long> getPlayerOverallDiceDashboard(long playerId) {
        return turnService.findOverallDiceCountByPlayerId(playerId).stream()
                .collect(Collectors.toMap(PlayerDiceRollsCount::outcome, PlayerDiceRollsCount::count));
    }

    public List<GameDTO.GamePlayerDTO> computeGamePlayerStatisticsWithView(
            long gameId, List<GameDTO.GamePlayerDTO> gamePlayers) {
        var turns = turnViewRepository.findByGameIdOrderByPlayerIdAsc(gameId);
        var maxLargestArmy = turns.stream().mapToLong(TurnView::getLastTurnLargestArmy).max().orElseGet(() -> -1L);
        var maxLongestRoad = turns.stream().mapToLong(TurnView::getLastTurnLongestRoad).max().orElseGet(() -> -1L);;
        var statistics = turns.stream().map(turn -> {
            var statisticsDTO = turnViewMapper.toGamePlayerStatisticsDTO(turn);
            if (turn.getLastTurnLargestArmy() == maxLargestArmy) {
                statisticsDTO.setLargestArmy(true);
            }
            if (turn.getLastTurnLongestRoad() == maxLongestRoad) {
                statisticsDTO.setLongestRoad(true);
            }
            return statisticsDTO;
        }).toList();
        return StreamUtils.zip(
                        gamePlayers.stream().sorted(), statistics.stream(), gamePlayerMapper::updateDtoWithStatistics)
                .map(this::computePlayerScore)
                .toList();
    }

    public List<GameDTO.GamePlayerDTO> computeGamePlayerStatistics(
            long gameId, List<GameDTO.GamePlayerDTO> gamePlayers) {
        var statistics = turnService.computeGamePlayerStatistics(gameId);
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
        return turnService.countByGameId(gameId) - numOfPlayers;
    }
}
