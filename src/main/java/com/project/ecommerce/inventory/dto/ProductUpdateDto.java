package com.project.ecommerce.inventory.dto;

import com.project.ecommerce.inventory.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateDto(
        @NotNull Long id,

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
