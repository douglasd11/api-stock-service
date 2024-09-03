package com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponseDto toCategoryResponseDto(Category category);
    Category toCategory(CategoryResponseDto categoryResponseDto);

}
