package com.pragma_2024_2.api_stock_service.domain.api.usecase;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.BrandAlreadyExistsException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.pragma_2024_2.api_stock_service.domain.api.IBrandServicePort;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import com.pragma_2024_2.api_stock_service.domain.spi.IBrandPersistencePort;


public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public Brand saveBrand(Brand brand) {
        if (brandPersistencePort.getBrandByName(brand.getName()) != null) {
            throw new BrandAlreadyExistsException();
        }
        return brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Brand getBrandById(Long id) {
        Brand brand = brandPersistencePort.getBrandById(id);
        if (brand == null) {
            throw new ElementNotFoundException();
        }
        return brand;
    }

    @Override
    public Brand getBrandByName(String name) {
        Brand brand = brandPersistencePort.getBrandByName(name);
        if (brand == null) {
            throw new ElementNotFoundException();
        }
        return brand;
    }

}
