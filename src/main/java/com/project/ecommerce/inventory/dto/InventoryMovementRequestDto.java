package com.project.ecommerce.inventory.dto;

import jakarta.validation.constraints.NotNull;

public record InventoryMovementRequestDto(
        @NotNull
        Long productId,

        @NotNull
        int quantity
) {
}
