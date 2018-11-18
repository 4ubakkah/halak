package com.halak.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

    private Long gameId;

    @JsonIgnore
    private List<PitDto> pits = new ArrayList<>();

    private String activePlayer;

    @JsonProperty("status")
    public List<ImmutableMap<Integer, Integer>> getMapOfPits() {
        List<ImmutableMap<Integer, Integer>> list = new ArrayList<>();

        pits.sort(Comparator.comparingInt(PitDto::getIndex));

        pits.forEach(pitDto ->  list.add(ImmutableMap.of(pitDto.getIndex(), pitDto.getStonesCount())));

        return list;
    }
}
