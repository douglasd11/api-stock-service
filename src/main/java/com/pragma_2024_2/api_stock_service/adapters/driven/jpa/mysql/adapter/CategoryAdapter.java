package com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public Category saveCategory(Category category) {
        return categoryEntityMapper.toModel(categoryRepository.save(categoryEntityMapper.toEntity(category)));
    }

    @Override
    public Category getCategoryByName(String name) {
        Optional<CategoryEntity> category = categoryRepository.findByName(name);
        return categoryEntityMapper.toModel(category.orElse(null));
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        return categoryEntityMapper.toModel(category.orElse(null));
    }

}
