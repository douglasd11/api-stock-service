package com.pragma_2024_2.api_stock_service.domain.api;

import com.pragma_2024_2.api_stock_service.domain.model.Category;


public interface ICategoryServicePort {

    Category saveCategory(Category category);

    Category getCategoryByName(String name);

    Category getCategoryById(Long id);

}
