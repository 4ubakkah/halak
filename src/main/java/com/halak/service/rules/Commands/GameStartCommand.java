package com.halak.service.rules.Commands;

import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

//TODO do we need this one?
// Added this one for possibility to enhance functionality, at the moment does not carry useful functionality
public class GameStartCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);

        if (!gameContext.isStarted()) {
            gameContext.setStarted(true);
        }

        return false;
    }
}
