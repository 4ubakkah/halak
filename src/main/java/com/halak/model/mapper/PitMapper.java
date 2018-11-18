package com.halak.model.mapper;

import com.halak.model.dto.PitDto;
import com.halak.model.entity.PitEntity;

import java.util.List;

public interface PitMapper {

    PitDto toDto(PitEntity pit);

    List<PitDto> toDtoList(List<PitEntity> pits);
}
