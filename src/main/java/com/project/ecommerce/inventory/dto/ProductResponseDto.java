package com.project.ecommerce.inventory.dto;

import com.project.ecommerce.inventory.entity.Product;
import java.math.BigDecimal;

public record ProductResponseDto(
        Long id,
        String productName,
        String color,
        String size,
        int quantity,
        BigDecimal price,
        Boolean isActive
) {
    public ProductResponseDto(Product product){
        this(
                product.getId(),
                product.getProductName(),
                product.getColor(),
                product.getSize(),
                product.getQuantity(),
                product.getPrice(),
                product.getIsActive()
        );
    }
}
