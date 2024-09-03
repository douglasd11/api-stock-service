package com.pragma_2024_2.api_stock_service.domain.spi;

import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;

public interface ICategoryPersistencePort {

    Category saveCategory(Category category);

    CustomPage<Category> getAllCategories(Integer page, Integer size, String direction);

    Category getCategoryByName(String name);

    Category getCategoryById(Long id);

}
