package com.halak.service.rules.Commands;

import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class GameContinuationCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);

        if(gameContext.isComplete()) {
            throw new RuntimeException("Game is complete already, create new game to be able to play again.");
        }

        return false;
    }
}
