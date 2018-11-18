package com.halak.persistance;

import com.halak.model.entity.GameState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class GameStateRepositoryTest {

    @Autowired
    private GameStateRepository gameStateRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("[Should] find game by id [Given] existing game id")
    public void findGameById() {
        GameState persistedGameState = entityManager.persist(new GameState());

        Optional<GameState> gameById = gameStateRepository.findGameById(persistedGameState.getId());

        assertThat(gameById).isPresent();
        assertThat(gameById).hasValueSatisfying(gameState -> gameState.getId().equals(persistedGameState.getId()));
    }

    @Test
    @DisplayName("[Should Not] find game by id [Given] existing game id")
    public void findGameByIdFail() {
        Long gameWithThisIdDoesNotEvenExistHaha = -1L;
        Optional<GameState> gameById = gameStateRepository.findGameById(gameWithThisIdDoesNotEvenExistHaha);

        assertThat(gameById).isNotPresent();
    }
}