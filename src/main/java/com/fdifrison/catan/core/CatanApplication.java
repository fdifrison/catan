package com.fdifrison.catan.core;

import com.fdifrison.catan.core.dto.GameDTO;
import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.dto.TurnDTO;
import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.service.GameService;
import com.fdifrison.catan.core.service.PlayerService;
import com.fdifrison.catan.core.service.StatisticsService;
import java.util.List;
import net.datafaker.Faker;
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
    CommandLineRunner commandLineRunner(
            GameService gameService, PlayerService playerService, StatisticsService statisticsService) {
        return args -> {
            var p1 = new PlayerDTO(
                    1,
                    "Giova",
                    "ing.giovanni.frison@gmail.com",
                    "https://i.imgur.com/jt0Uubi.png",
                    false);
            var p2 = new PlayerDTO(
                    2,
                    "Mich",
                  "michele.roberti7@gmail.com",
                    "https://i.imgur.com/GdNzR8P.png",
                    false);
            var p3 = new PlayerDTO(
                    3,
                    "Pisco",
                    "l.piscagli92@gmail.com",
                    "https://i.imgur.com/vnNKlTi.png",
                    false);
            var p4 = new PlayerDTO(
                    4,
                    "Berna",
                    "luca.bernabini1989@gmail.com",
                    "https://i.imgur.com/vy3NMkP.png",
                    false);
            var p5 = new PlayerDTO(
                    5,
                    "Cata",
                    "luca.catalani1@gmail.com",
                    "https://i.imgur.com/Ejm5crd.png",
                    false);
            var p6 = new PlayerDTO(
                    6,
                    "Jolly",
                     "jolly@email.com",
                    "https://i.imgur.com/Q0woEmX.png",
                    false);
            var p1Id = playerService.newPlayer(p1);
            var p2Id = playerService.newPlayer(p2);
            var p3Id = playerService.newPlayer(p3);
            var p4Id = playerService.newPlayer(p4);
            var p5Id = playerService.newPlayer(p5);
            var p6Id = playerService.newPlayer(p6);

//            var po1 =
//                    new GameSetupDTO.GamePlayerInfoDTO(1, p1Id, 3, faker.color().hex());
//            var po2 =
//                    new GameSetupDTO.GamePlayerInfoDTO(2, p2Id, 5, faker.color().hex());
//            var po3 =
//                    new GameSetupDTO.GamePlayerInfoDTO(3, p3Id, 1, faker.color().hex());
//            var po4 =
//                    new GameSetupDTO.GamePlayerInfoDTO(4, p4Id, 4, faker.color().hex());
//            var po5 =
//                    new GameSetupDTO.GamePlayerInfoDTO(5, p5Id, 2, faker.color().hex());
//
//            var gameId = gameService.createGame(new GameSetupDTO(
//                    new GameSetupDTO.SetupDTO(faker.book().title(), Game.GameType.STANDARD, 14),
//                    List.of(po1, po2, po3, po4, po5)));
//
//            for (int i = 0; i < 10; i++) {
//                GameDTO gameDTO = gameService.getGameDTOByGameId(gameId);
//                int max = gameDTO.gamePlayers().stream()
//                        .mapToInt(GameDTO.GamePlayerDTO::plainScore)
//                        .max()
//                        .getAsInt();
//                if (max > gameDTO.gameInfo().requiredVictoryPoints()) break;
//                for (Long p : List.of(p1Id, p2Id, p3Id, p4Id, p5Id)) {
//                    var turnDTO = new TurnDTO(
//                            gameId,
//                            p,
//                            faker.random().nextInt(2, 12),
//                            faker.random().nextInt(0, 2),
//                            faker.bool().bool(),
//                            faker.bool().bool(),
//                            faker.bool().bool(),
//                            faker.random().nextInt(0, 1),
//                            faker.random().nextInt(0, 1),
//                            faker.random().nextInt(0, 1));
//                    gameService.newTurn(gameId, turnDTO);
//                }
//            }
//            GameDTO gameDTO = gameService.getGameDTOByGameId(gameId);
//
//            // set winner for the player which has the most points
//            gameService.endGame(gameId, gameDTO.gamePlayers());
//            System.out.println();
        };
    }
}
