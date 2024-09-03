package com.pragma_2024_2.api_stock_service.adapters.driving.http.controller;


import com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request.BrandRequestDto;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.IBrandRequestMapper;
import com.pragma_2024_2.api_stock_service.adapters.driving.http.mapper.IBrandResponseMapper;
import com.pragma_2024_2.api_stock_service.domain.api.IBrandServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
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

}

