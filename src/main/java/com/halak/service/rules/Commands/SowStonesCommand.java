package com.halak.service.rules.Commands;

import com.halak.model.exception.NonEligibleMoveException;
import com.halak.model.game.GameBoard;
import com.halak.model.player.Player;
import com.halak.service.rules.GameUtilities;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;

public class SowStonesCommand implements Command {

    @Override
    public boolean execute(Context context) {
        int selectedPitId = (int) context.get("selectedPitId");
        Player activePlayer = (Player) context.get("activePlayer");
        List<Player> players = (List<Player>) context.get("players");
        GameBoard gameBoard = (GameBoard) context.get("gameBoard");

        int stonesCount = gameBoard.getPit(selectedPitId).extractStones();

        if (stonesCount == 0) {
            throw new NonEligibleMoveException("You selected pitId: [%s]. You can't sow from empty pit!", selectedPitId);
        }

        int startingPitId = selectedPitId + 1;
        int lastSownPitId = startingPitId % gameBoard.getCountOfPits();

        for (int i = 0; i < stonesCount; i++) {
            if (getOpponentsKalahId(players, activePlayer).equals(lastSownPitId)) {
                lastSownPitId = (lastSownPitId + 1) % gameBoard.getCountOfPits();
            }

            if (!getOpponentsKalahId(players, activePlayer).equals(lastSownPitId)) {
                gameBoard.getPit(lastSownPitId).putStone();
                lastSownPitId = (lastSownPitId + 1) % gameBoard.getCountOfPits();
            }
        }

        context.put("lastSownPitId", lastSownPitId - 1);

        return false;
    }

    private Integer getOpponentsKalahId(List<Player> players, Player activePlayer) {
        return GameUtilities.findOpponent(players, activePlayer).getKalahIndex();
    }
}
