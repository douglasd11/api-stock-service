package com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    Brand toModel(BrandEntity brandEntity);
    BrandEntity toEntity(Brand brand);

}
