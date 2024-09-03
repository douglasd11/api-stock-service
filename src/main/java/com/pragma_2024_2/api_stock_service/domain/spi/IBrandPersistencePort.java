package com.pragma_2024_2.api_stock_service.domain.spi;

import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;


public interface IBrandPersistencePort {

    Brand saveBrand(Brand brand);

    CustomPage<Brand> getAllBrands(Integer page, Integer size, String direction);

    Brand getBrandByName(String name);

    Brand getBrandById(Long id);

}
