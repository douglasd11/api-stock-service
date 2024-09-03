package com.bootcamp_2024_2.api_stock_service.categoryTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.bootcamp_2024_2.api_stock_service.testdata.TestData;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.util.Constants;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a category, it should save it in the database")

    void testSaveCategory() {
        //GIVEN
        Category category = TestData.getCategory();
        CategoryEntity categoryEntity = new CategoryEntity();

        given(categoryEntityMapper.toEntity(category)).willReturn(categoryEntity);
        given(categoryEntityMapper.toModel(categoryEntity)).willReturn(category);
        given(categoryRepository.save(categoryEntity)).willReturn(categoryEntity);

        //WHEN
        Category result = categoryAdapter.saveCategory(category);

        //THEN
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    @DisplayName("Given a category, it should return all categories")
    void testGetCategoryByName_CategoryExists() {
        //GIVEN
        Category category = TestData.getCategory();
        CategoryEntity categoryEntity = new CategoryEntity();

        given(categoryRepository.findByName(category.getName())).willReturn(Optional.of(categoryEntity));
        given(categoryEntityMapper.toModel(categoryEntity)).willReturn(category);

        //WHEN
        Category result = categoryAdapter.getCategoryByName(category.getName());

        //THEN
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    void testGetCategoryById_CategoryExists() {
        //GIVEN
        Category category = TestData.getCategory();
        CategoryEntity categoryEntity = new CategoryEntity();

        given(categoryRepository.findById(category.getId())).willReturn(Optional.of(categoryEntity));
        given(categoryEntityMapper.toModel(categoryEntity)).willReturn(category);

        //WHEN
        Category result = categoryAdapter.getCategoryById(category.getId());

        //THEN
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    @DisplayName("Test get all categories when there are categories")
    void testGetAllCategories() {
        //GIVEN
        Category category = TestData.getCategory();
        List<CategoryEntity> categoriesEntity = List.of(new CategoryEntity(category.getId(), category.getName(), category.getDescription()));
        Page<CategoryEntity> categoriesEntityPage = new PageImpl<>(categoriesEntity);

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc(Constants.FIELD_NAME)));

        given(categoryRepository.findAll(pageable)).willReturn(categoriesEntityPage);

        //WHEN
        CustomPage<Category> result = categoryAdapter.getAllCategories(0, 10, "asc");

        //THEN
        assertNotNull(result);
        assertEquals(List.of(category).size(), result.getContent().size());
    }

}
