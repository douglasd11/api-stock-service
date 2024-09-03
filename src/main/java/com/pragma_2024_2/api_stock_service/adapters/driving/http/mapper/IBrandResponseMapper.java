package com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper;


import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {

    BrandResponseDto toBrandResponseDto(Brand brand);
    Brand toBrand(BrandResponseDto brandResponseDto);

}
