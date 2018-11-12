package com.halak.model.mapper.impl;

import com.halak.model.game.pit.Pit;
import com.halak.model.dto.PitDto;
import com.halak.model.mapper.PitMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PitMapperImpl implements PitMapper {

    @Override
    public PitDto toDto(Pit pit) {
        return PitDto.builder().stonesCount(pit.getStones()).build();
    }

    @Override
    public List<PitDto> toDtoList(List<Pit> pits) {
        return pits.stream().map(this::toDto).collect(Collectors.toList());
    }
}
