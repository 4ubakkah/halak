package com.halak.model.game.pit;

import lombok.Data;

@Data
public class Pit {

    private int stones;

    private boolean isKalah;

    protected Pit(int stones, boolean isKalah) {
        this.stones = stones;
        this.isKalah = isKalah;
    }

    public int extractStones() {
        int stonesCount = stones;
        stones = 0;
        return stonesCount;
    }

    public void putStone() {
        stones++;
    }

    public void addStones(int stones) {
        this.stones = this.stones + stones;
    }
}
