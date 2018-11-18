package com.halak.persistance;

import com.halak.model.entity.GameState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameStateRepository extends JpaRepository<GameState, Long> {

    Optional<GameState> findGameById(Long id);
}
