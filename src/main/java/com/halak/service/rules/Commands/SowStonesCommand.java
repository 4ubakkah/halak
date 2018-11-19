package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.PlayerEntity;
import com.halak.service.rules.GameContext;
import com.halak.service.rules.GameUtilities;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;

import static com.halak.configuration.GameSpecifications.PITS_COUNT_OVERALL;

/**
 * Active player is sowing stones starting from selected pit index.
 * On the start of the move player extracts all stones from selected pit and moving counter-clockwise puts one stone in each following pit,
 * excluding opponent's Kalah but including his own.
 * */
public class SowStonesCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);

        int selectedPitIndex = gameContext.getSelectedPitIndex();
        PlayerEntity activePlayer = gameContext.getActivePlayer();
        List<PlayerEntity> players = gameContext.getPlayers();
        GameBoardEntity gameBoard = gameContext.getGameState().getGameBoard();

        // Get stones out of selected pit
        int stonesCount = gameBoard.getPit(selectedPitIndex).extractStones();

        // At start last sown pit index is set to selected pit, on the first iteration we are starting to sown from pit next to selected one
        int lastSownPitIndex = selectedPitIndex;

        for (int i = 0; i < stonesCount; i++) {
            // Switch to opponents pits if needed
            lastSownPitIndex = (lastSownPitIndex + 1) % PITS_COUNT_OVERALL;

            // if next pit is opponent's Kalah - skip it without putting stone inside
            if (!getOpponentsKalahId(players, activePlayer).equals(lastSownPitIndex)) {
                gameBoard.getPit(lastSownPitIndex).putStone();
            }
        }

        // Save last sown pit index before proceeding in chain
        gameContext.setLastSownPitIndex(lastSownPitIndex);

        return false;
    }

    private Integer getOpponentsKalahId(List<PlayerEntity> players, PlayerEntity activePlayer) {
        return GameUtilities.findOpponent(players, activePlayer).getKalahIndex();
    }
}
