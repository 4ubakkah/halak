package com.halak.model.entity;

import com.halak.model.entity.factory.PitEntityFactory;
import com.halak.model.exception.NonExistingPitIndexException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.halak.configuration.GameSpecifications.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name = "GAME_BOARD")
public class GameBoardEntity extends BaseEntity{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PitEntity> pits = new ArrayList<>(PITS_COUNT_OVERALL);

    public GameBoardEntity() {
        fillPitsWithStones();
    }

    private void fillPitsWithStones() {
        pits = IntStream.range(0, (PITS_COUNT_PER_PLAYER + KALAHS_COUNT_PER_PLAYER) * PLAYERS_COUNT)
                .mapToObj(i -> isKalah(i) ? PitEntityFactory.kalahPit(i) : PitEntityFactory.pit(i))
                .collect(Collectors.toList());
    }

    public boolean isKalah(int index) {
        //6th and 13th indexed pits are special Kalah pits
        return (index == PITS_COUNT_PER_PLAYER) || (index == PITS_COUNT_PER_PLAYER * PLAYERS_COUNT + KALAHS_COUNT_PER_PLAYER);
    }

    public PitEntity getPit(int index) {
        if (index < 0 || index >= PITS_COUNT_OVERALL) {
            throw new NonExistingPitIndexException("Provided pit index: {%s} is outside of game board boundaries", index);
        }

        // due to all the checks previously we are pretty much sure that we are able to retrieve one pit entry here
        return pits.stream().filter(pit -> pit.getIndex() == index).findFirst().get();
    }

    public List<PitEntity> getPitsInRange(int fromIndex, int toIndex) {
        return pits.subList(fromIndex, toIndex);
    }
}
