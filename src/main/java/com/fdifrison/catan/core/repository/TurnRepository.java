package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {}
