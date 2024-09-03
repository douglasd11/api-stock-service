package com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request.BrandRequestDto;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBrandRequestMapper {

    @Mapping(target = "id", ignore = true)
    Brand requestToBrand(BrandRequestDto brandRequestDto);

}
