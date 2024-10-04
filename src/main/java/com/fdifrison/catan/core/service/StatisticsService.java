package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.DiceDashboardDTO;
import com.fdifrison.catan.core.repository.TurnRepository;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    private final TurnRepository turnRepository;

    public StatisticsService(TurnRepository turnRepository) {
        this.turnRepository = turnRepository;
    }

    public DiceDashboardDTO getGameDiceDashboard(long gameId) {
        var diceDashboard = new DiceDashboardDTO();
        turnRepository
                .findDiceCountByGameId(gameId)
                .forEach(dice -> diceDashboard.getDiceCountMap().replace(dice.outcome(), dice.count()));
        return diceDashboard;
    }

    public DiceDashboardDTO getPlayerOverallDiceDashboard(long playerId) {
        var diceDashboard = new DiceDashboardDTO();
        turnRepository
                .findOverallDiceCountByPlayerId(playerId)
                .forEach(dice -> diceDashboard.getDiceCountMap().replace(dice.outcome(), dice.count()));
        return diceDashboard;
    }

    public void dummy(long gameId) {
        var playerAggregateGameStatisticsByGameId = turnRepository.findPlayerAggregateGameStatisticsByGameId(gameId);
        System.out.println();
    }
}
