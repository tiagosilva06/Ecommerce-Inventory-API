package com.project.ecommerce.inventory.dto;

import com.project.ecommerce.inventory.entity.OrderItem;

import java.math.BigDecimal;

public record OrderItemResponseDto(String productName, Integer quantity, BigDecimal price) {

    public OrderItemResponseDto(OrderItem item){
        this(
                item.getProduct().getProductName(),
                item.getProduct().getQuantity(),
                item.getProduct().getPrice()
        );
    }
}
