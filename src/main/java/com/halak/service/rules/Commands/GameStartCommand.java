package com.halak.service.rules.Commands;

import com.halak.model.game.GameBoard;
import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class GameStartCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        GameContext gameContext = getGameContext(context);

        if (!gameContext.isStarted()) {
            gameContext.setGameBoard(new GameBoard());
            gameContext.setStarted(true);
        }

        return false;
    }
}
