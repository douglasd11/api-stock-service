package com.bootcamp_2024_2.api_stock_service.brandTest;

import com.bootcamp_2024_2.api_stock_service.testdata.TestData;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.controller.BrandRestControllerAdapter;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request.BrandRequestDto;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.IBrandRequestMapper;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma_2024_2.api_stock_service.domain.api.IBrandServicePort;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class BrandRestControllerAdapterTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandRequestMapper brandRequestMapper;

    @Mock
    private IBrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandRestControllerAdapter brandRestControllerAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a brand, it should save it in the database")
    void testAddBrand() {
        //GIVEN
        BrandRequestDto request = new BrandRequestDto("Electronics", "Brand for electronics");
        Brand brand = TestData.getBrand();

        given(brandRequestMapper.requestToBrand(request)).willReturn(brand);

        //WHEN
        ResponseEntity<Void> response = brandRestControllerAdapter.addBrand(request);

        //THEN
        verify(brandServicePort, times(1)).saveBrand(brand);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Get all brands from the database")
    void testGetAllBrands() {
        //GIVEN
        int page = 0;
        int size = 10;
        String direction = Constants.ORDER_ASC;

        CustomPage<Brand> brands = new CustomPage<>();
        BrandPaginationResponseDto<BrandResponseDto> list = new BrandPaginationResponseDto<>();

        given(brandServicePort.getAllBrands(page, size, direction)).willReturn(brands);
        given(brandResponseMapper.toBrandPaginationResponseDto(brands)).willReturn(list);

        //WHEN
        ResponseEntity<BrandPaginationResponseDto<BrandResponseDto>> response = brandRestControllerAdapter.getAllBrands(page, size, direction);

        //THEN
        assertEquals(list.getContent(), response.getBody().getContent());
    }

}