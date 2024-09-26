package com.fdifrison.catan.core;

import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.dto.PlayerScoreDTO;
import com.fdifrison.catan.core.service.GameService;
import com.fdifrison.catan.core.service.PlayerService;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatanApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatanApplication.class, args);
    }


    CommandLineRunner commandLineRunner(GameService gameService, PlayerService playerService) {
        return args -> {
            var p1Id = playerService.newPlayer(new PlayerDTO("aaa", "aaa@email", null));
            var p2Id = playerService.newPlayer(new PlayerDTO("bbb", "bbb@email", null));
            var p3Id = playerService.newPlayer(new PlayerDTO("ccc", "ccc@email", null));
            var p1 = new PlayerScoreDTO(p1Id, 3);
            var p2 = new PlayerScoreDTO(p2Id, 1);
            var p3 = new PlayerScoreDTO(p3Id, 2);
            var newGame = gameService.createNewGame(List.of(p1, p2, p3));
            var p1f = new PlayerScoreDTO(p1Id, 3, false, false, 5, 2);
            var p2f = new PlayerScoreDTO(p2Id, 1, false, true, 8, 2);
            var p3f = new PlayerScoreDTO(p3Id, 2, true, false, 12, 2);
            gameService.updateScoreAndEndGame(newGame, List.of(p1f, p2f, p3f));
            var gameRanking = gameService.getGameRanking(newGame);
            gameRanking.forEach(System.out::println);
        };
    }
}
