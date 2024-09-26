package com.fdifrison.catan.core;

import com.fdifrison.catan.core.service.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CatanApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatanApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(GameService gameService) {
        return args -> {
            var newGame = gameService.createNewGame(List.of(1L, 2L));
            System.out.println(newGame);
        };
    }
}
