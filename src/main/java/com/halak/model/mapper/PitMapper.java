package com.halak.model.mapper;

import com.halak.model.game.pit.Pit;
import com.halak.model.dto.PitDto;

import java.util.List;

public interface PitMapper {

    PitDto toDto(Pit pit);

    List<PitDto> toDtoList(List<Pit> pits);
}
