package com.halak.model.game.pit;

import com.halak.model.game.GameSpecifications;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PitFactory {

    public static Pit pit() {
        return new Pit(GameSpecifications.STONES_COUNT_PER_PIT, false);
    }

    public static Pit kalahPit() {
        return new Pit(0, true);
    }
}
