package com.pragma_2024_2.api_stock_service.adapters.driving.http.controller;

import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request.CategoryRequestDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryPaginationResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.pragma_2024_2.api_stock_service.domain.api.ICategoryServicePort;

import io.swagger.v3.oas.annotations.media.Schema;
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


    @GetMapping("/")
    @Operation(
            summary = "Retrieve all categories",
            description = "Fetches a paginated list of all categories. This endpoint supports pagination through 'page' and 'size' query parameters."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of categories", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public CategoryPaginationResponseDto<CategoryResponseDto> getAllCategories(
            @Parameter(description = "Page number for pagination (0-based index)", example = "0")
            @RequestParam Integer page,

            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam Integer size,

            @Parameter(description = "Sort direction, either 'asc' for ascending or 'desc' for descending", example = "asc")
            @RequestParam String direction
    ) {

        return categoryResponseMapper.toCategoryPaginationResponseDto(categoryServicePort.getAllCategories(page, size, direction));
    }

}

