package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.Game;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {

    @EntityGraph(attributePaths = "playerScores")
    Optional<Game> findWithScoreById(long id);
}
