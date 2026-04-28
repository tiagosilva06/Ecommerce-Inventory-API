package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.dto.OrderItemRequestDto;
import com.project.ecommerce.inventory.dto.OrderRequestDto;
import com.project.ecommerce.inventory.entity.Order;
import com.project.ecommerce.inventory.entity.Product;
import com.project.ecommerce.inventory.repository.OrderRepository;
import com.project.ecommerce.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldCreateOrderSuccessfully() {
        // ARRANGE
        Long productId = 1L;

        Product product = new Product();
        product.setId(productId);
        product.setProductName("Vestido Midi");
        product.setQuantity(20);
        product.setPrice(new BigDecimal("100.00"));

        OrderItemRequestDto itemDto = new OrderItemRequestDto(productId, 2);

        OrderRequestDto request = new OrderRequestDto(
                "Tiago",
                List.of(itemDto)
        );

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        Order result = orderService.createOrder(request);

        // ASSERT

        // pedido criado
        assertNotNull(result);

        // total calculado (100 * 2)
        assertEquals(0, result.getTotalAmount().compareTo(new BigDecimal("200.00")));

        // estoque reduzido (20 - 2)
        assertEquals(18, product.getQuantity());

        // item criado
        assertEquals(1, result.getItems().size());

        // repository chamado
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}