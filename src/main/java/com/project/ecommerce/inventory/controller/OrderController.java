package com.project.ecommerce.inventory.controller;

import com.project.ecommerce.inventory.dto.OrderRequestDto;
import com.project.ecommerce.inventory.dto.OrderResponseDto;
import com.project.ecommerce.inventory.entity.Order;
import com.project.ecommerce.inventory.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody @Valid OrderRequestDto request, UriComponentsBuilder uriBuilder){

        Order order = orderService.createOrder(request);

        var uri = uriBuilder
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new OrderResponseDto(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {

        var orders = orderService.getAllOrders()
                .stream()
                .map(OrderResponseDto::new)
                .toList();

        return ResponseEntity.ok(orders);
    }
}
