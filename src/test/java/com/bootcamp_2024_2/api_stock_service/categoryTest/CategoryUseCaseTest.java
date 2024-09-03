package com.bootcamp_2024_2.api_stock_service.categoryTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.bootcamp_2024_2.api_stock_service.testdata.TestData;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma_2024_2.api_stock_service.domain.api.usecase.CategoryUseCase;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.spi.ICategoryPersistencePort;


import com.pragma_2024_2.api_stock_service.domain.util.Constants;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @Test
    @DisplayName("Given a category, should it be saved in the database?")
    void testSaveCategory() {
        //GIVEN
        Category category = TestData.getCategory();
        given(categoryPersistencePort.saveCategory(category)).willReturn(category);

        //WHEN
        Category result = categoryUseCase.saveCategory(category);

        //THEN
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    @DisplayName("Given a category, the name is repeated, it should throw an exception")
    void testSaveCategory_CategoryAlreadyExists() {
        //GIVEN
        Category category = TestData.getCategory();
        given(categoryPersistencePort.getCategoryByName(category.getName())).willReturn(category);

        //WHEN & THEN
        assertThrows(CategoryAlreadyExistsException.class, () ->
            categoryUseCase.saveCategory(category)
        );
    }

    @Test
    @DisplayName("Test get category by name when category exists")
    void testGetCategoryByName_CategoryExists() {
        //GIVEN
        Category category = TestData.getCategory();
        given(categoryPersistencePort.getCategoryByName(category.getName())).willReturn(category);

        //WHEN
        Category result = categoryUseCase.getCategoryByName(category.getName());

        //THEN
        assertEquals(category, result);
    }

    @Test
    @DisplayName("Should throw ElementNotFoundException when category does not exist")
    void testGetCategoryByName_CategoryNotExists() {
        // GIVEN
        Category category = TestData.getCategory();
        String categoryName = category.getName();
        given(categoryPersistencePort.getCategoryByName(categoryName)).willReturn(null);

        // WHEN & THEN
        assertThrows(ElementNotFoundException.class, () ->
                categoryUseCase.getCategoryByName(categoryName)
        );
    }

    @Test
    @DisplayName("Test get category by id when category exists")
    void testGetCategoryById_CategoryExists() {
        //GIVEN
        Category category = TestData.getCategory();
        given(categoryPersistencePort.getCategoryById(category.getId())).willReturn(category);

        //WHEN
        Category result = categoryUseCase.getCategoryById(category.getId());

        //THEN
        assertEquals(category, result);
    }

    @Test
    @DisplayName("Should throw ElementNotFoundException when category does not exist")
    void testGetCategoryById_CategoryNotExists() {
        // GIVEN
        Category category = TestData.getCategory();
        Long categoryId = category.getId();
        given(categoryPersistencePort.getCategoryById(categoryId)).willReturn(null);

        // WHEN & THEN
        assertThrows(ElementNotFoundException.class, () ->
                categoryUseCase.getCategoryById(categoryId)
        );
    }

    @Test
    @DisplayName("Test get all categories when there are categories")
    void testGetAllCategories() {
        //GIVEN
        CustomPage<Category> categories =
                new CustomPage<>(List.of(TestData.getCategory()), 0, 10, 1, 1, true, false);
        given(categoryPersistencePort.getAllCategories(0, 10, Constants.ORDER_ASC)).willReturn(categories);

        //WHEN
        CustomPage<Category> result = categoryUseCase.getAllCategories(0, 10, Constants.ORDER_ASC);

        //THEN
        assertEquals(categories, result);
    }

    @Test
    @DisplayName("Should throw NoDataFoundException when there are no categories")
    void testGetAllCategories_NoDataFound() {
        // GIVEN
        CustomPage<Category> categories = new CustomPage<>();
        given(categoryPersistencePort.getAllCategories(0, 10, Constants.ORDER_ASC)).willReturn(categories);

        // WHEN & THEN
        assertThrows(NoDataFoundException.class, () ->
                categoryUseCase.getAllCategories(0, 10, Constants.ORDER_ASC)
        );
    }

}