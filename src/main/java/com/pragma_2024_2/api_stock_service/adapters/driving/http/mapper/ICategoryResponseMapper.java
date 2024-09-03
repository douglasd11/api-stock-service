package com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {

    CategoryResponseDto toCategoryResponseDto(Category category);
    Category toCategory(CategoryResponseDto categoryResponseDto);

    CategoryPaginationResponseDto<CategoryResponseDto> toCategoryPaginationResponseDto(CustomPage<Category> categories);

}
