package com.pragma_2024_2.api_stock_service.adapters.driving.http.controller;


import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request.BrandRequestDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.BrandPaginationResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.BrandResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.response.CategoryResponseDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.IBrandRequestMapper;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma_2024_2.api_stock_service.domain.api.IBrandServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestControllerAdapter {

    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @PostMapping("/")
    @Operation(summary = "Add a new brand", description = "Creates a new brand and saves it in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<Void> addBrand(@Valid @RequestBody @Parameter(description = "Brand details to be added") BrandRequestDto request) {
        brandServicePort.saveBrand(brandRequestMapper.requestToBrand(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/")
    @Operation(
            summary = "Retrieve all brands",
            description = "Fetches a paginated list of all brands. This endpoint supports pagination through 'page' and 'size' query parameters."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of brands", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<BrandPaginationResponseDto<BrandResponseDto>> getAllBrands(
            @Parameter(description = "Page number for pagination (0-based index)", example = "0")
            @RequestParam Integer page,

            @Parameter(description = "Number of items per page", example = "10")
            @RequestParam Integer size,

            @Parameter(description = "Sort direction, either 'asc' for ascending or 'desc' for descending", example = "asc")
            @RequestParam String direction
    ) {

        return ResponseEntity.ok(brandResponseMapper.toBrandPaginationResponseDto(brandServicePort.getAllBrands(page, size, direction)));
    }

}

