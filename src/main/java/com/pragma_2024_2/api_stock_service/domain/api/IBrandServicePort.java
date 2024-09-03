package com.pragma_2024_2.api_stock_service.domain.api;

import com.pragma_2024_2.api_stock_service.domain.model.Brand;


public interface IBrandServicePort {

    Brand saveBrand(Brand brand);

    Brand getBrandByName(String name);

    Brand getBrandById(Long id);

}
