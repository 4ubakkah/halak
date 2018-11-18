package com.halak.model.entity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PitEntityFactory {

    public static PitEntity pit(int index) {
        return new PitEntity(index,false);
    }

    public static PitEntity kalahPit(int index) {
        return new PitEntity(index, true);
    }
}
