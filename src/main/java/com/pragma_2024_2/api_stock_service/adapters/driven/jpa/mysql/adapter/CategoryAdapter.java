package com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.spi.ICategoryPersistencePort;
import com.pragma_2024_2.api_stock_service.domain.util.Constants;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
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
    public CustomPage<Category> getAllCategories(Integer page, Integer size, String direction) {

        Sort.Order order = new Sort.Order(Sort.Direction.fromString(direction), Constants.FIELD_NAME);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        Page<CategoryEntity> categoriesPage = categoryRepository.findAll(pageable);

        List<Category> categoryContent = categoriesPage.getContent()
                .stream()
                .map(category -> new Category(category.getId(), category.getName(), category.getDescription()))
                .toList();

        return new CustomPage<>(
                categoryContent,
                categoriesPage.getNumber(),
                categoriesPage.getSize(),
                categoriesPage.getTotalElements(),
                categoriesPage.getTotalPages(),
                categoriesPage.isFirst(),
                categoriesPage.isLast()
        );

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
