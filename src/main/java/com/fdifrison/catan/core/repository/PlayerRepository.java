package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.Player;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {
    List<Player> findAllByIdIn(List<Long> playerIds);
}
