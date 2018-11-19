package com.halak.service.rules.Commands;

import com.halak.model.exception.NonEligibleMoveException;
import com.halak.configuration.GameSpecifications;
import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * Validates selected input. If selected pit index is outside of game board range - throws exception and breaks the game change
 */
public class ValidateInputCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);

        if (gameContext.getSelectedPitIndex() >= GameSpecifications.PITS_COUNT_OVERALL || gameContext.getSelectedPitIndex() < 0) {
            throw new NonEligibleMoveException("Selected pitId: [%s] is outside of game board range", gameContext.getSelectedPitIndex());
        }

        return false;
    }
}
