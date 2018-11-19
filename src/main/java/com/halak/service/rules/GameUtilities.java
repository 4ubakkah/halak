package com.halak.service.rules;

import com.halak.configuration.GameSpecifications;
import com.halak.model.entity.PlayerEntity;
import com.halak.model.exception.PlayerAllocationException;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GameUtilities {

    /**
     * Returns opponent of currently active player
     */
    public static PlayerEntity findOpponent(List<PlayerEntity> players, PlayerEntity activePlayer) {
        return players.stream()
                .filter(player -> !player.equals(activePlayer))
                .findFirst()
                .orElseThrow(() -> new PlayerAllocationException("Error allocating player: couldn't find opponent"));
    }

    /**
     * Checks if provided pit index does belong to range of player's regular pits
     */
    public static boolean regularPitBelongsToPlayer(PlayerEntity player, int pitIndex) {
        return pitIndex < player.getKalahIndex() && pitIndex >= player.getKalahIndex() - GameSpecifications.PITS_COUNT_PER_PLAYER;
    }
}
