package com.halak.model.game;

import com.halak.model.exception.UnableToRetrieveStonesException;
import com.halak.model.game.pit.Pit;
import com.halak.model.game.pit.PitFactory;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static com.halak.model.game.GameSpecifications.*;

@ToString
public class GameBoard {

    @Getter
    private final List<Pit> pits = new ArrayList<>(PITS_COUNT_OVERALL);/*= Arrays.asList(new Pit[14]);*/

    public GameBoard() {
        fillPitsWithStones();
    }

    private void fillPitsWithStones() {
        for (int i = 0; i < (PITS_COUNT_PER_PLAYER + KALAHS_COUNT_PER_PLAYER) * PLAYERS_COUNT; i++) {
            if (!isKalah(i)) {
                pits.add(i, PitFactory.pit());
            } else {
                pits.add(i, PitFactory.kalahPit());
            }
        }
    }

    public boolean isKalah(int pitId) {
        //todo cleanup
        return (pitId == 6) || (pitId == 13);
    }

    public int getCountOfPits() {
        return pits.size();
    }

    public Pit getPit(int pitId) {
        if (pitId < 0 || pitId >= pits.size()) {
            throw new UnableToRetrieveStonesException("Provided pitId: {%s} is outside of game board boundaries", pitId);
        }

        return pits.get(pitId);
    }

    public List<Pit> getPitsInRange(int fromIndex, int toIndex) {
        return pits.subList(fromIndex, toIndex);
    }
}
