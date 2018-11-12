package com.halak.service.rules;

import com.halak.model.exception.PlayerAllocationException;
import com.halak.model.player.Player;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GameUtilities {

    public static Player findOpponent(List<Player> players, Player activePlayer) {
        return players.stream()
                .filter(player -> !player.equals(activePlayer))
                .findFirst()
                .orElseThrow(() -> new PlayerAllocationException("Error allocating player: couldn't find opponent"));
    }
}
