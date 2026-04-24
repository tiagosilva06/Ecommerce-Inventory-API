package com.project.ecommerce.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequestDto(@NotNull Long productId, @NotNull @Positive Integer quantity) {
}
