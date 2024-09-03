package com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper;


import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {

    BrandResponseDto toBrandResponseDto(Brand brand);
    Brand toBrand(BrandResponseDto brandResponseDto);

    BrandPaginationResponseDto<BrandResponseDto> toBrandPaginationResponseDto(CustomPage<Brand> brands);

}
