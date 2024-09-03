package com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryResponseDto {

    private final Long id;
    private final String name;
    private final String description;
}
