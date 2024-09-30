package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.Turn;
import com.fdifrison.catan.core.entity.projection.DiceRollsCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

    @Query(
            value = """
                    select new
                    com.fdifrison.catan.core.entity.projection.DiceRollsCount
                    (t.outcome, count(t.outcome))
                    from Turn t where t.game.id = :gameId
                    group by t.outcome
                    order by t.outcome
                    """)
    List<DiceRollsCount> findDiceCountByGameId(@Param("gameId") long gameId);

    @Query(
            value = """
                    select new
                    com.fdifrison.catan.core.entity.projection.DiceRollsCount
                    (t.outcome, count(t.outcome))
                    from Turn t where t.player.id = :playerId
                    group by t.outcome
                    order by t.outcome
                    """)
    List<DiceRollsCount> findOverallDiceCountByPlayerId(@Param("playerId") long playerId);

}
