package com.fdifrison.catan.core.service;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.PlayerScoreDTO;
import com.fdifrison.catan.core.dto.mapper.GameMapper;
import com.fdifrison.catan.core.dto.mapper.PlayerScoreMapper;
import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.exception.GameNotFoundException;
import com.fdifrison.catan.core.repository.GameRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Long createNewGame(@Valid List<PlayerScoreDTO> players) {
        var playerList = players.stream()
                .distinct()
                .map(PlayerScoreMapper.INSTANCE::toEntity)
                .toList();
        return gameRepository.save(new Game().setPlayerScores(playerList)).getId();
    }

    public GameDTO updateScoreAndEndGame(long id, @Valid List<PlayerScoreDTO> players) {
        var game = gameRepository.findWithScoreById(id).orElseThrow(GameNotFoundException::new);
        var playerScoreList =
                players.stream().map(PlayerScoreMapper.INSTANCE::toEntity).toList();
        game.setPlayerScores(playerScoreList);
        gameRepository.saveAndFlush(game);
        return GameMapper.INSTANCE.toDto(game);
    }

    public List<PlayerScoreDTO> getGameRanking(long id) {
        var game = gameRepository.findWithScoreById(id).orElseThrow(GameNotFoundException::new);
        return game.getPlayerScores().stream()
                .map(PlayerScoreMapper.INSTANCE::toDto)
                .toList();
    }
}
