package com.project.ecommerce.inventory.dto;

import com.project.ecommerce.inventory.entity.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(Long id, String customerName, BigDecimal totalAmount, LocalDateTime orderDate,
                               OrderStatus status, List<OrderItemResponseDto> items) {
}
