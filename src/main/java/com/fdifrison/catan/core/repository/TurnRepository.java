package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.Turn;
import com.fdifrison.catan.core.entity.projection.PlayerDiceRollsCount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

    @Query(
            value =
                    """
                    select new
                    com.fdifrison.catan.core.entity.projection.PlayerDiceRollsCount
                    (t.player.id, t.outcome, count(t.outcome))
                    from Turn t where t.game.id = :gameId and t.outcome > 0
                    group by t.outcome, t.player.id
                    order by t.player.id, t.outcome
                    """)
    List<PlayerDiceRollsCount> findDiceCountByGameId(@Param("gameId") long gameId);

    @Query(
            value =
                    """
                    select new
                    com.fdifrison.catan.core.entity.projection.PlayerDiceRollsCount
                    (t.player.id, t.outcome, count(t.outcome))
                    from Turn t where t.player.id = :playerId
                    group by t.outcome
                    order by t.outcome
                    """)
    List<PlayerDiceRollsCount> findOverallDiceCountByPlayerId(@Param("playerId") long playerId);

    long countByGameId(long gameId);
}
