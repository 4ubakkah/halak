package com.halak.service.rules.Commands;

import com.halak.model.entity.GameState;
import com.halak.model.exception.UnsupportedContextType;
import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ContextBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AbstractGameCommandTest {

    @Test
    @DisplayName("[Should] return gameContext by type converting context")
    void getGameContext() {
        AbstractGameCommand gameCommand = new AbstractGameCommand() {
        };

        Context context = new GameContext(GameState.builder().build(), 0, 0, "");
        GameContext gameContext = gameCommand.getGameContext(context);

        assertThat(gameContext).isEqualTo(context);
    }

    @Test
    @DisplayName("[Should] return gameContext by type converting context")
    void getGameContext_throwsException() {
        AbstractGameCommand gameCommand = new AbstractGameCommand() {
        };

        Context context = new ContextBase(new HashMap());

        assertThatThrownBy(() -> gameCommand.getGameContext(context)).isInstanceOf(UnsupportedContextType.class).hasMessageContaining("Unsupported context type");
    }
}