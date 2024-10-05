package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.mapper.GamePlayerMapper;
import com.fdifrison.catan.core.entity.projection.PlayerDiceRollsCount;
import com.fdifrison.catan.core.repository.TurnRepository;
import java.util.List;
import java.util.Map;
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

    public Map<Long, Map<Long, Long>> getGameDiceDashboard(long gameId) {
        return turnRepository.findDiceCountByGameId(gameId).stream()
                .collect(Collectors.groupingBy(
                        PlayerDiceRollsCount::playerId,
                        Collectors.toMap(PlayerDiceRollsCount::outcome, PlayerDiceRollsCount::count)));
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
