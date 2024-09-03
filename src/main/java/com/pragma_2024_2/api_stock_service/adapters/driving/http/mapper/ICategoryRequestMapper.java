package com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request.CategoryRequestDto;

import com.pragma_2024_2.api_stock_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface ICategoryRequestMapper {

    @Mapping(target = "id", ignore = true)
    Category requestToCategory(CategoryRequestDto categoryRequestDto);

}
