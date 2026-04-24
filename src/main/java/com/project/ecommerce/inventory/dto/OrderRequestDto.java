package com.project.ecommerce.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequestDto(

        @NotBlank
        String customerName,

        @NotEmpty
        List<OrderItemRequestDto> items
) {
}
