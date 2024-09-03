package com.pragma_2024_2.api_stock_service.adapters.driving.http.dto.request;


import com.pragma_2024_2.api_stock_service.domain.util.Constants;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
public class BrandRequestDto {

    @NotBlank(message = Constants.FIELD_NAME_NULL_MESSAGE)
    @Size(max = 50, message = Constants.FIELD_NAME_EXCEEDS_SIZE_MESSAGE)
    private final String name;

    @NotBlank(message = Constants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(max = 90, message = Constants.FIELD_DESCRIPTION_EXCEEDS_SIZE_MESSAGE)
    private final String description;

    public BrandRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
