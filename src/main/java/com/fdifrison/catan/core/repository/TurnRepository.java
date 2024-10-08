package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.Turn;
import com.fdifrison.catan.core.entity.projection.GamePlayerStatistics;
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

    @Query(
            value =
                    """
                    with last_true as (
                        select
                            max(case when t1.longest_road = true then t1.id else 0 end) as last_longest_road,
                            max(case when t1.largest_army = true then t1.id else 0 end) as last_largest_army
                        from Turn t1
                        where t1.game_id = :gameId
                    )
                    select
                        p.id,
                        sum(t.develop_card_drawn) as developcarddrawn,
                        count(case when t.knight_card_played then 1 end) as knightcardplayed,
                        sum(t.roads_built) as roadsbuilt,
                        sum(t.colonies_built) as coloniesbuilt,
                        sum(t.cities_built) as citiesbuilt,
                        case
                            when max(case when t.longest_road = true then t.id else -1 end) = lt.last_longest_road
                                then true
                            else false
                            end as longestRoad,
                        case
                            when max(case when t.largest_army = true then t.id else -1 end) = lt.last_largest_army
                                then true
                            else false
                            end as largestArmy
                    from Turn t
                    join Player p on p.id = t.player_id
                    cross join last_true lt
                    where t.game_id = :gameId
                    group by p.id, lt.last_longest_road, lt.last_largest_army
                    order by p.id asc
                    """,
            nativeQuery = true)
    List<GamePlayerStatistics> computeGamePlayerStatistics(@Param("gameId") long gameId);

    long countByGameId(long gameId);
}
