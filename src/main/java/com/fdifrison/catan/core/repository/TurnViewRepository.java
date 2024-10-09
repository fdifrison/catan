package com.fdifrison.catan.core.repository;

import com.fdifrison.catan.core.entity.TurnView;

import java.util.List;

public interface TurnViewRepository extends ReadOnlyRepository<TurnView, Long> {
    List<TurnView> findByGameIdOrderByPlayerIdAsc(Long gameId);
}
