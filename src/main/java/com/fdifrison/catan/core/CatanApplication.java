package com.fdifrison.catan.core;

import com.fdifrison.catan.core.dto.GameSetupDTO;
import com.fdifrison.catan.core.dto.PlayerDTO;
import com.fdifrison.catan.core.entity.Game;
import com.fdifrison.catan.core.service.GameService;
import com.fdifrison.catan.core.service.PlayerService;
import com.fdifrison.catan.core.service.StatisticsService;
import com.fdifrison.catan.core.service.TurnService;
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
            GameService gameService,
            PlayerService playerService,
            TurnService turnService,
            StatisticsService statisticsService) {
        return args -> {
            var faker = new Faker();

            var p1 = new PlayerDTO(
                    1,
                    faker.pokemon().name(),
                    faker.name().firstName().toLowerCase() + "@email.com",
                    faker.avatar().image(),
                    false);
            var p2 = new PlayerDTO(
                    2,
                    faker.pokemon().name(),
                    faker.name().firstName().toLowerCase() + "@email.com",
                    faker.avatar().image(),
                    false);
            var p3 = new PlayerDTO(
                    3,
                    faker.pokemon().name(),
                    faker.name().firstName().toLowerCase() + "@email.com",
                    faker.avatar().image(),
                    false);
            var p4 = new PlayerDTO(
                    4,
                    faker.pokemon().name(),
                    faker.name().firstName().toLowerCase() + "@email.com",
                    faker.avatar().image(),
                    false);
            var p5 = new PlayerDTO(
                    5,
                    faker.pokemon().name(),
                    faker.name().firstName().toLowerCase() + "@email.com",
                    faker.avatar().image(),
                    false);
            var p1Id = playerService.newPlayer(p1);
            var p2Id = playerService.newPlayer(p2);
            var p3Id = playerService.newPlayer(p3);
            var p4Id = playerService.newPlayer(p4);
            var p5Id = playerService.newPlayer(p5);

            var po1 =
                    new GameSetupDTO.GamePlayerInfoDTO(1, p1Id, 1, faker.color().hex());
            var po2 =
                    new GameSetupDTO.GamePlayerInfoDTO(2, p2Id, 2, faker.color().hex());
            var po3 =
                    new GameSetupDTO.GamePlayerInfoDTO(3, p3Id, 3, faker.color().hex());
            var po4 =
                    new GameSetupDTO.GamePlayerInfoDTO(4, p4Id, 4, faker.color().hex());
            var po5 =
                    new GameSetupDTO.GamePlayerInfoDTO(5, p5Id, 5, faker.color().hex());

            var newGame = gameService.createGame(new GameSetupDTO(
                    new GameSetupDTO.GameInfoDTO(faker.book().title(), Game.GameType.STANDARD, 5, 14),
                    List.of(po1, po2, po3, po4, po5)));

            System.out.println();
            //            for (Long p : List.of(p1Id, p2Id, p3Id)) {
            //                for (int i = 0; i < 25; i++) {
            //                    var dice = faker.random().nextInt(2, 12);
            //                    turnService.initTurn(new InitTurnDTO(newGame, p, dice));
            //                }
            //            }
            //
            //            var p1f = new PlayerScoreDTO(
            //                    p1Id,
            //                    3,
            //                    faker.bool().bool(),
            //                    faker.bool().bool(),
            //                    faker.random().nextInt(3, 14),
            //                    faker.random().nextInt(0, 3));
            //            var p2f = new PlayerScoreDTO(
            //                    p2Id,
            //                    1,
            //                    faker.bool().bool(),
            //                    faker.bool().bool(),
            //                    faker.random().nextInt(3, 14),
            //                    faker.random().nextInt(0, 3));
            //            var p3f = new PlayerScoreDTO(
            //                    p3Id,
            //                    2,
            //                    faker.bool().bool(),
            //                    faker.bool().bool(),
            //                    faker.random().nextInt(3, 14),
            //                    faker.random().nextInt(0, 3));
            //            gameService.updateScoreAndEndGame(newGame, List.of(p1f, p2f, p3f));

            //            statisticsService.getGameDiceDashboard(newGame);
            //            var gameRanking = gameService.getGameRanking(newGame);
            //            gameRanking.forEach(System.out::println);
        };
    }
}
