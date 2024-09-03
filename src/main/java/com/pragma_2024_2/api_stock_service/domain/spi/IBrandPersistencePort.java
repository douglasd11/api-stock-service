package com.pragma_2024_2.api_stock_service.domain.spi;

import com.pragma_2024_2.api_stock_service.domain.model.Brand;


public interface IBrandPersistencePort {

    Brand saveBrand(Brand brand);

    Brand getBrandByName(String name);

    Brand getBrandById(Long id);

}
