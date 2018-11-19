package com.halak.service.rules.Commands;

import com.halak.model.exception.GameIsAlreadyCompleteException;
import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * Decides if game can be continued, if game is complete already - throws exception and breaks the game chain
 */
public class GameContinuationCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);

        if(gameContext.isComplete()) {
            throw new GameIsAlreadyCompleteException("Game is complete already, create new game to be able to play again. Game id: {%s}", gameContext.getGameState().getId());
        }

        return false;
    }
}
