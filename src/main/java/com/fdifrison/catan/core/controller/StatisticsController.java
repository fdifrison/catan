package com.fdifrison.catan.core.controller;

import com.fdifrison.catan.core.dto.DiceDashboardDTO;
import com.fdifrison.catan.core.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("{gameId}/dice-dashboard")
    public DiceDashboardDTO getGameDiceDashboard(@PathVariable("gameId") long gameId) {
        return statisticsService.getGameDiceDashboard(gameId);
    }

    @GetMapping("{playerId}/dice-dashboard")
    public DiceDashboardDTO getPlayerOverallDiceDashboard(@PathVariable("playerId") long playerId) {
        return statisticsService.getPlayerOverallDiceDashboard(playerId);
    }
}
