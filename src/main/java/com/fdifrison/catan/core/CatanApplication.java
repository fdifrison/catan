package com.fdifrison.catan.core;

import com.fdifrison.catan.core.dto.InitTurnDTO;
import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.dto.PlayerScoreDTO;
import com.fdifrison.catan.core.service.GameService;
import com.fdifrison.catan.core.service.PlayerService;
import com.fdifrison.catan.core.service.StatisticsService;
import com.fdifrison.catan.core.service.TurnService;

import java.util.List;
import java.util.Random;

import org.instancio.Instancio;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatanApplication {

    public static void main(String[] args) {
        SpringDocUtils.getConfig().addParentType(PlayerDTO.class.getSimpleName());
        SpringApplication.run(CatanApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(GameService gameService, PlayerService playerService, TurnService turnService, StatisticsService statisticsService) {
        return args -> {
            var p1Id = playerService.newPlayer(Instancio.of(PlayerDTO.class).create());
            var p2Id = playerService.newPlayer(Instancio.of(PlayerDTO.class).create());
            var p3Id = playerService.newPlayer(Instancio.of(PlayerDTO.class).create());
            var p1 = new PlayerScoreDTO(p1Id, 3);
            var p2 = new PlayerScoreDTO(p2Id, 1);
            var p3 = new PlayerScoreDTO(p3Id, 2);
            var newGame = gameService.createNewGame(List.of(p1, p2, p3));
            Random random = new Random();
            for (Long p : List.of(p1Id, p2Id, p3Id)) {
                for (int i = 0; i < 20; i++) {
                    var dice = random.nextInt((12 - 2) + 1) + 2;
                    turnService.initTurn(new InitTurnDTO(newGame, p, dice));
                }
            }
//            statisticsService.getGameDiceDashboard(newGame);

            var p1f = new PlayerScoreDTO(p1Id, 3, false, false, 5, 2);
            var p2f = new PlayerScoreDTO(p2Id, 1, false, true, 8, 2);
            var p3f = new PlayerScoreDTO(p3Id, 2, true, false, 12, 2);
            gameService.updateScoreAndEndGame(newGame, List.of(p1f, p2f, p3f));
//            var gameRanking = gameService.getGameRanking(newGame);
//            gameRanking.forEach(System.out::println);
        };
    }
}
