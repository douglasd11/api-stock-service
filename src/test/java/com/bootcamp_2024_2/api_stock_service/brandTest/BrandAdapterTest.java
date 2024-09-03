package com.bootcamp_2024_2.api_stock_service.brandTest;


import com.bootcamp_2024_2.api_stock_service.testdata.TestData;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.adapter.BrandAdapter;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


class BrandAdapterTest {

    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @InjectMocks
    private BrandAdapter brandAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a brand, it should save it in the database")
    void testSaveBrand() {
        //GIVEN
        Brand brand = TestData.getBrand();
        BrandEntity brandEntity = new BrandEntity();

        given(brandEntityMapper.toEntity(brand)).willReturn(brandEntity);
        given(brandEntityMapper.toModel(brandEntity)).willReturn(brand);
        given(brandRepository.save(brandEntity)).willReturn(brandEntity);

        //WHEN
        Brand result = brandAdapter.saveBrand(brand);

        //THEN
        assertNotNull(result);
        assertEquals(brand, result);
    }

    @Test
    @DisplayName("Given a brand, it should return all brands")
    void testGetBrandByName_BrandExists() {
        //GIVEN
        Brand brand = TestData.getBrand();
        BrandEntity brandEntity = new BrandEntity();

        given(brandRepository.findByName(brand.getName())).willReturn(Optional.of(brandEntity));
        given(brandEntityMapper.toModel(brandEntity)).willReturn(brand);

        //WHEN
        Brand result = brandAdapter.getBrandByName(brand.getName());

        //THEN
        assertNotNull(result);
        assertEquals(brand, result);
    }

    @Test
    void testGetBrandById_BrandExists() {
        //GIVEN
        Brand brand = TestData.getBrand();
        BrandEntity brandEntity = new BrandEntity();

        given(brandRepository.findById(brand.getId())).willReturn(Optional.of(brandEntity));
        given(brandEntityMapper.toModel(brandEntity)).willReturn(brand);

        //WHEN
        Brand result = brandAdapter.getBrandById(brand.getId());

        //THEN
        assertNotNull(result);
        assertEquals(brand, result);
    }

    @Test
    @DisplayName("Test get all brands when there are brands")
    void testGetAllBrands() {
        //GIVEN
        Brand brand = TestData.getBrand();
        List<BrandEntity> brandsEntity = List.of(new BrandEntity(brand.getId(), brand.getName(), brand.getDescription()));
        Page<BrandEntity> brandsEntityPage = new PageImpl<>(brandsEntity);

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.asc(Constants.FIELD_NAME)));

        given(brandRepository.findAll(pageable)).willReturn(brandsEntityPage);

        //WHEN
        CustomPage<Brand> result = brandAdapter.getAllBrands(0, 10, "asc");

        //THEN
        assertNotNull(result);
        assertEquals(List.of(brand).size(), result.getContent().size());
    }

}
