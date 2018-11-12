package com.halak.service.rules.Commands;

import com.halak.model.game.GameBoard;
import com.halak.model.game.GameSpecifications;
import com.halak.model.game.pit.Pit;
import com.halak.model.player.Player;
import com.halak.service.rules.GameUtilities;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;

public class CaptureStonesCommand implements Command {

    @Override
    public boolean execute(Context context) {
        Player activePlayer = (Player) context.get("activePlayer");
        List<Player> players = (List<Player>) context.get("players");
        int lastSownPitId = (int) context.get("lastSownPitId");
        int selectedPitId = (int) context.get("selectedPitId");
        GameBoard gameBoard = (GameBoard) context.get("gameBoard");

        if (!GameUtilities.findOpponent(players, activePlayer).equals(lastSownPitId) && pitBelongsToPlayer(activePlayer, selectedPitId)) {
            if (gameBoard.getPit(selectedPitId).getStones() == 1) {
                int oppositePitId = Math.abs(lastSownPitId - 12);

                if (gameBoard.getPit(oppositePitId).getStones() > 0) {
                    int accumulatedStones = gameBoard.getPit(oppositePitId).extractStones() + gameBoard.getPit(lastSownPitId).extractStones();
                    Pit playersKalah = gameBoard.getPit(activePlayer.getKalahIndex());
                    playersKalah.addStones(accumulatedStones);
                }
            }
        }

        return false;
    }

    private boolean pitBelongsToPlayer(Player activePlayer, int pitId) {
        return pitId < activePlayer.getKalahIndex() && pitId >= activePlayer.getKalahIndex() - GameSpecifications.PITS_COUNT_PER_PLAYER;
    }
}
