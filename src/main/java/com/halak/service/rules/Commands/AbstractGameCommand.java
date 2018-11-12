package com.halak.service.rules.Commands;

import com.halak.model.exception.UnsupportedContextType;
import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Context;

public abstract class AbstractGameCommand {

    protected GameContext getGameContext(Context context) {
        if (context instanceof GameContext) {
            return (GameContext) context;
        }

        throw new UnsupportedContextType("Unsupported context type detected: [%s]", context.getClass());
    }
}
