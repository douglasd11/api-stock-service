package com.pragma_2024_2.api_stock_service.domain.api.usecase;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma_2024_2.api_stock_service.domain.api.ICategoryServicePort;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.spi.ICategoryPersistencePort;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;


public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category saveCategory(Category category) {
        if (categoryPersistencePort.getCategoryByName(category.getName()) != null) {
            throw new CategoryAlreadyExistsException();
        }
        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public CustomPage<Category> getAllCategories(Integer page, Integer size, String direction) {
        CustomPage<Category> categories = categoryPersistencePort.getAllCategories(page, size, direction);

        if (categories.getContent() == null) {
            throw new NoDataFoundException();
        }
        return categoryPersistencePort.getAllCategories(page, size, direction);
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryPersistencePort.getCategoryById(id);
        if (category == null) {
            throw new ElementNotFoundException();
        }
        return category;
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = categoryPersistencePort.getCategoryByName(name);
        if (category == null) {
            throw new ElementNotFoundException();
        }
        return category;
    }

}
