package com.project.ecommerce.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreateDto(
        @NotBlank
        String productName,

        @NotBlank
        String color,

        @NotBlank
        String size,

        @NotNull
        int quantity,

        @NotNull
        BigDecimal price
) {
}
