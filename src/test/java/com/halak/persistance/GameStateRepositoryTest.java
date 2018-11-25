package com.halak.persistance;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.GameState;
import com.halak.model.entity.PlayerEntity;
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
        GameState gameState = new GameState();
        gameState.setActivePlayerEntity(new PlayerEntity());
        GameBoardEntity gameBoardEntity = new GameBoardEntity();
        gameState.setGameBoard(gameBoardEntity);
        GameState persistedGameState = entityManager.persist(gameState);

        Optional<GameState> gameById = gameStateRepository.findGameById(persistedGameState.getId());

        assertThat(gameById).isPresent();
        assertThat(gameById).hasValueSatisfying(gs -> gameState.getId().equals(persistedGameState.getId()));
    }

    @Test
    @DisplayName("[Should Not] find game by id [Given] existing game id")
    public void findGameByIdFail() {
        Long gameWithThisIdDoesNotEvenExistHaha = -1L;
        Optional<GameState> gameById = gameStateRepository.findGameById(gameWithThisIdDoesNotEvenExistHaha);

        assertThat(gameById).isNotPresent();
    }
}