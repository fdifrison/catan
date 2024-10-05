package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {
    List<Player> findAllByIdIn(List<Long> playerIds);
}
