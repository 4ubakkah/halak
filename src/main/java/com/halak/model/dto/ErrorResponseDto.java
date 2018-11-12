package com.halak.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponseDto {

    public ErrorResponseDto(String description) {
        this.description = description;
    }

    private String description;
}
