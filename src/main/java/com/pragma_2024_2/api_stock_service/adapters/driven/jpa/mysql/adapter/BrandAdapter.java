package com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import com.pragma_2024_2.api_stock_service.domain.spi.IBrandPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public Brand saveBrand(Brand brand) {
        return brandEntityMapper.toModel(brandRepository.save(brandEntityMapper.toEntity(brand)));
    }

    @Override
    public Brand getBrandByName(String name) {
        Optional<BrandEntity> brand = brandRepository.findByName(name);
        return brandEntityMapper.toModel(brand.orElse(null));
    }

    @Override
    public Brand getBrandById(Long id) {
        Optional<BrandEntity> brand = brandRepository.findById(id);
        return brandEntityMapper.toModel(brand.orElse(null));
    }

}
