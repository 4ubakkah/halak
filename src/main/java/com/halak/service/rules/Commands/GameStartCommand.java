package com.halak.service.rules.Commands;

import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * Added this one as it felt natural, at the moment does not carry useful functionality
 */
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
