package com.bootcamp_2024_2.api_stock_service.brandTest;

import com.bootcamp_2024_2.api_stock_service.testdata.TestData;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.BrandAlreadyExistsException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.pragma_2024_2.api_stock_service.domain.api.usecase.BrandUseCase;

import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.spi.IBrandPersistencePort;


import com.pragma_2024_2.api_stock_service.domain.util.Constants;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @Test
    @DisplayName("Given a brand, it should be saved in the database")
    void testSaveBrand() {
        //GIVEN
        Brand brand = TestData.getBrand();
        given(brandPersistencePort.saveBrand(brand)).willReturn(brand);

        //WHEN
        Brand result = brandUseCase.saveBrand(brand);

        //THEN
        assertNotNull(result);
        assertEquals(brand, result);
    }

    @Test
    @DisplayName("Given a Brand, the name is repeated, it should throw an exception")
    void testSaveBrand_BrandAlreadyExists() {
        //GIVEN
        Brand brand = TestData.getBrand();
        given(brandPersistencePort.getBrandByName(brand.getName())).willReturn(brand);

        //WHEN & THEN
        assertThrows(BrandAlreadyExistsException.class, () ->
                brandUseCase.saveBrand(brand)
        );
    }

    @Test
    @DisplayName("Test get brand by name when brand exists")
    void testGetBrandByName_BrandExists() {
        //GIVEN
        Brand brand = TestData.getBrand();
        given(brandPersistencePort.getBrandByName(brand.getName())).willReturn(brand);

        //WHEN
        Brand result = brandUseCase.getBrandByName(brand.getName());

        //THEN
        assertEquals(brand, result);
    }

    @Test
    @DisplayName("Should throw ElementNotFoundException when brand does not exist")
    void testGetBrandByName_BrandNotExists() {
        // GIVEN
        Brand brand = TestData.getBrand();
        String brandName = brand.getName();
        given(brandPersistencePort.getBrandByName(brandName)).willReturn(null);

        // WHEN & THEN
        assertThrows(ElementNotFoundException.class, () ->
                brandUseCase.getBrandByName(brandName)
        );
    }

    @Test
    @DisplayName("Test get brand by id when brand exists")
    void testGetBrandById_BrandExists() {
        //GIVEN
        Brand brand = TestData.getBrand();
        given(brandPersistencePort.getBrandById(brand.getId())).willReturn(brand);

        //WHEN
        Brand result = brandUseCase.getBrandById(brand.getId());

        //THEN
        assertEquals(brand, result);
    }

    @Test
    @DisplayName("Should throw ElementNotFoundException when brand does not exist")
    void testGetBrandById_BrandNotExists() {
        // GIVEN
        Brand brand = TestData.getBrand();
        Long brandId = brand.getId();
        given(brandPersistencePort.getBrandById(brandId)).willReturn(null);

        // WHEN & THEN
        assertThrows(ElementNotFoundException.class, () ->
                brandUseCase.getBrandById(brandId)
        );
    }

    @Test
    @DisplayName("Test get all brands when there are brands")
    void testGetAllBrands() {
        //GIVEN
        CustomPage<Brand> brands =
                new CustomPage<>(List.of(TestData.getBrand()), 0, 10, 1, 1, true, false);
        given(brandPersistencePort.getAllBrands(0, 10, Constants.ORDER_ASC)).willReturn(brands);

        //WHEN
        CustomPage<Brand> result = brandUseCase.getAllBrands(0, 10, Constants.ORDER_ASC);

        //THEN
        assertEquals(brands, result);
    }

    @Test
    @DisplayName("Should throw NoDataFoundException when there are no brands")
    void testGetAllBrands_NoDataFound() {
        // GIVEN
        CustomPage<Brand> brands = new CustomPage<>();
        given(brandPersistencePort.getAllBrands(0, 10, Constants.ORDER_ASC)).willReturn(brands);

        // WHEN & THEN
        assertThrows(NoDataFoundException.class, () ->
                brandUseCase.getAllBrands(0, 10, Constants.ORDER_ASC)
        );
    }

}