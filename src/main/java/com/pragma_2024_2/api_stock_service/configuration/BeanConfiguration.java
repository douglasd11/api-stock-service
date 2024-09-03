package com.pragma_2024_2.api_stock_service.configuration;

import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.pragma_2024_2.api_stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.pragma_2024_2.api_stock_service.domain.api.ICategoryServicePort;
import com.pragma_2024_2.api_stock_service.domain.api.usecase.CategoryUseCase;
import com.pragma_2024_2.api_stock_service.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

}
