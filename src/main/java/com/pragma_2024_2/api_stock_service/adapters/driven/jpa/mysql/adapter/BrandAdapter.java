package com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.adapter;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.BrandEntity;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper.IBrandEntityMapper;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.repository.IBrandRepository;
import com.pragma_2024_2.api_stock_service.domain.model.Brand;
import com.pragma_2024_2.api_stock_service.domain.model.Category;
import com.pragma_2024_2.api_stock_service.domain.spi.IBrandPersistencePort;
import com.pragma_2024_2.api_stock_service.domain.util.Constants;
import com.pragma_2024_2.api_stock_service.domain.util.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
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
    public CustomPage<Brand> getAllBrands(Integer page, Integer size, String direction) {

        Sort.Order order = new Sort.Order(Sort.Direction.fromString(direction), Constants.FIELD_NAME);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));

        Page<BrandEntity> brandsPage = brandRepository.findAll(pageable);

        List<Brand> brandContent = brandsPage.getContent()
                .stream()
                .map(brand -> new Brand(brand.getId(), brand.getName(), brand.getDescription()))
                .toList();

        return new CustomPage<>(
                brandContent,
                brandsPage.getNumber(),
                brandsPage.getSize(),
                brandsPage.getTotalElements(),
                brandsPage.getTotalPages(),
                brandsPage.isFirst(),
                brandsPage.isLast()
        );
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
