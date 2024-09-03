package com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    Category toModel(CategoryEntity categoryEntity);
    CategoryEntity toEntity(Category category);

}
