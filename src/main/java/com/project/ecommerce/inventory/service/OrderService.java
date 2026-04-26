package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.dto.OrderRequestDto;
import com.project.ecommerce.inventory.entity.Order;
import com.project.ecommerce.inventory.repository.OrderRepository;
import com.project.ecommerce.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order createOrder(OrderRequestDto request){

        return null;
    }

}
