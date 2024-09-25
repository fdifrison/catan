package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.GamePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Integer> {}
