package com.pragma_2024_2.api_stock_service.adapters.driving.http.controller;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request.CategoryRequestDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma_2024_2.api_stock_service.domain.api.ICategoryServicePort;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestControllerAdapter {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @PostMapping("/")
    @Operation(summary = "Add a new category", description = "Creates a new category and saves it in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<Void> addCategory(@Valid @RequestBody @Parameter(description = "Category details to be added") CategoryRequestDto request) {
        categoryServicePort.saveCategory(categoryRequestMapper.requestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

