package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.DiceDashboardDTO;
import com.fdifrison.catan.core.service.StatisticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("game-dice-dashboard")
    public DiceDashboardDTO getGameDiceDashboard(@RequestParam("gameId") long gameId) {
        return statisticsService.getGameDiceDashboard(gameId);
    }

    @GetMapping("player-dice-dashboard")
    public DiceDashboardDTO getPlayerOverallDiceDashboard(@RequestParam("playerId") long playerId) {
        return statisticsService.getPlayerOverallDiceDashboard(playerId);
    }
}
