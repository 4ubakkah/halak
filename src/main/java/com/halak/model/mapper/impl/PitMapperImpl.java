package com.halak.model.mapper.impl;

import com.halak.model.dto.PitDto;
import com.halak.model.entity.PitEntity;
import com.halak.model.mapper.PitMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PitMapperImpl implements PitMapper {

    @Override
    public PitDto toDto(PitEntity pit) {
        return PitDto.builder().stonesCount(pit.getStonesCount()).index(pit.getIndex()).build();
    }

    @Override
    public List<PitDto> toDtoList(List<PitEntity> pits) {
        return pits.stream().map(this::toDto).collect(Collectors.toList());
    }
}
