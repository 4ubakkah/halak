package com.halak.model.entity;

import com.halak.configuration.GameSpecifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardEntityTest {

    @Test
    @DisplayName("[Should] intialize game board with proper pits content")
    void testInitialGameBoardState() {
        GameBoardEntity gameBoardEntity = new GameBoardEntity();

        gameBoardEntity.getPits().forEach(pit -> {
            if (pit.isKalah()) {
                // Initially all Kalah pits have 0 stones
                assertThat(pit.getStonesCount()).isEqualTo(GameSpecifications.INITIAL_STONES_COUNT_PER_KALAH);
            } else {
                // Initially all regular pits have 6 stones
                assertThat(pit.getStonesCount()).isEqualTo(GameSpecifications.INITIAL_STONES_COUNT_PER_PIT);
            }
        });
    }
}