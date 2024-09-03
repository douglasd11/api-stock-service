package com.bootcamp_2024_2.api_stock_service.categoryTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.bootcamp_2024_2.api_stock_service.testdata.TestData;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.controller.CategoryRestControllerAdapter;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request.CategoryRequestDto;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma_2024_2.api_stock_service.domain.api.ICategoryServicePort;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.util.Constants;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


class CategoryRestControllerAdapterTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ICategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryRestControllerAdapter categoryRestControllerAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a category, it should save it in the database")
    void testAddCategory() {
        //GIVEN
        CategoryRequestDto request = new CategoryRequestDto("Electronics", "Category for electronics");
        Category category = TestData.getCategory();

        given(categoryRequestMapper.requestToCategory(request)).willReturn(category);

        //WHEN
        ResponseEntity<Void> response = categoryRestControllerAdapter.addCategory(request);

        //THEN
        verify(categoryServicePort, times(1)).saveCategory(category);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Get all categories from the database")
    void testGetAllCategories() {
        //GIVEN
        int page = 0;
        int size = 10;
        String direction = Constants.ORDER_ASC;

        CustomPage<Category> categories = new CustomPage<>();
        CategoryPaginationResponseDto<CategoryResponseDto> list = new CategoryPaginationResponseDto<>();

        given(categoryServicePort.getAllCategories(page, size, direction)).willReturn(categories);
        given(categoryResponseMapper.toCategoryPaginationResponseDto(categories)).willReturn(list);

        //WHEN
        CategoryPaginationResponseDto<CategoryResponseDto> response = categoryRestControllerAdapter.getAllCategories(page, size, direction);

        //THEN
        assertEquals(list.getContent(), response.getContent());
    }

}