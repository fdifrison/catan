package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GamePlayerStatisticsDTO;
import com.fdifrison.catan.core.dto.mapper.TurnViewMapper;
import com.fdifrison.catan.core.entity.TurnView;
import com.fdifrison.catan.core.repository.TurnViewRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TurnViewService {

    private final TurnViewRepository turnViewRepository;
    private final TurnViewMapper turnViewMapper;

    public TurnViewService(TurnViewRepository turnViewRepository, TurnViewMapper turnViewMapper) {
        this.turnViewRepository = turnViewRepository;
        this.turnViewMapper = turnViewMapper;
    }

    public List<TurnView> findByGameIdOrderByPlayerIdAsc(long gameId) {
        return turnViewRepository.findByGameIdOrderByPlayerIdAsc(gameId);
    }

    protected List<GamePlayerStatisticsDTO> getGamePlayerStatisticsFromTurnViews(List<TurnView> turns) {
        var maxLargestArmy = turns.stream().mapToLong(TurnView::getLastTurnLargestArmy).max().orElse(0);
        var maxLongestRoad =
                turns.stream().mapToLong(TurnView::getLastTurnLongestRoad).max().orElse(0);
        return turns.stream()
                .map(turn -> {
                    var statisticsDTO = turnViewMapper.toGamePlayerStatisticsDTO(turn);
                    if (maxLargestArmy!= -1L && turn.getLastTurnLargestArmy() == maxLargestArmy) {
                        statisticsDTO.setLargestArmy(true);
                    }
                    if (maxLongestRoad!= -1L && turn.getLastTurnLongestRoad() == maxLongestRoad) {
                        statisticsDTO.setLongestRoad(true);
                    }
                    return statisticsDTO;
                })
                .toList();
    }
}
