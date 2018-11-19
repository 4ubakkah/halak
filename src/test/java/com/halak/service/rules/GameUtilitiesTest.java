package com.halak.service.rules;

import com.halak.model.entity.PlayerEntity;
import com.halak.model.entity.factory.PlayerEntityFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameUtilitiesTest {

    @Test
    @DisplayName("[Should] return opponent of given player")
    void findOpponent() {
        PlayerEntity firstPlayer = PlayerEntityFactory.firstPlayer();
        PlayerEntity secondPlayer = PlayerEntityFactory.secondPlayer();
        List<PlayerEntity> players = Arrays.asList(firstPlayer, secondPlayer);

        assertThat(firstPlayer).isNotEqualTo(secondPlayer);
        assertThat(GameUtilities.findOpponent(players, firstPlayer)).isEqualTo(secondPlayer);
        assertThat(GameUtilities.findOpponent(players, secondPlayer)).isEqualTo(firstPlayer);
    }

    @ParameterizedTest(name = "Run #{index} with pit index:[{arguments}]")
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5})
    @DisplayName("[Should] indicate that pit belongs to first player")
    void regularPitDoesBelongToFirstPlayer(int pitIndex) {
        PlayerEntity firstPlayer = PlayerEntityFactory.firstPlayer();

        boolean pitBelongsToPlayer = GameUtilities.regularPitBelongsToPlayer(firstPlayer, pitIndex);

        assertThat(pitBelongsToPlayer).isTrue();
    }

    @ParameterizedTest(name = "Run #{index} with pit index:[{arguments}]")
    @ValueSource(ints = { 7, 8, 9, 10, 11, 12, 13, 14, -1})
    @DisplayName("[Should Not] indicate that pit does belong to first player")
    void regularPitDoesNotBelongToFirstPlayer(int pitIndex) {
        PlayerEntity firstPlayer = PlayerEntityFactory.firstPlayer();

        boolean pitBelongsToPlayer = GameUtilities.regularPitBelongsToPlayer(firstPlayer, pitIndex);

        assertThat(pitBelongsToPlayer).isFalse();
    }

    @ParameterizedTest(name = "Run #{index} with pit index:[{arguments}]")
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 14, -1})
    @DisplayName("[Should Not] indicate that pit belongs to first player")
    void regularPitDoesNotBelongToSecondPlayer(int pitIndex) {
        PlayerEntity secondPlayer = PlayerEntityFactory.secondPlayer();

        boolean pitBelongsToPlayer = GameUtilities.regularPitBelongsToPlayer(secondPlayer, pitIndex);

        assertThat(pitBelongsToPlayer).isFalse();
    }

    @ParameterizedTest(name = "Run #{index} with pit index:[{arguments}]")
    @ValueSource(ints = { 7, 8, 9, 10, 11, 12})
    @DisplayName("[Should] indicate that pit does belong to first player")
    void regularPitDoesBelongToSecondPlayer(int pitIndex) {
        PlayerEntity secondPlayer = PlayerEntityFactory.secondPlayer();

        boolean pitBelongsToPlayer = GameUtilities.regularPitBelongsToPlayer(secondPlayer, pitIndex);

        assertThat(pitBelongsToPlayer).isTrue();
    }
}