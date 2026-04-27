package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.dto.OrderItemRequestDto;
import com.project.ecommerce.inventory.dto.OrderRequestDto;
import com.project.ecommerce.inventory.entity.Order;
import com.project.ecommerce.inventory.entity.OrderItem;
import com.project.ecommerce.inventory.entity.OrderStatus;
import com.project.ecommerce.inventory.entity.Product;
import com.project.ecommerce.inventory.exception.InsufficientInventoryException;
import com.project.ecommerce.inventory.exception.OrderNotFoundException;
import com.project.ecommerce.inventory.exception.ProductNotFoundException;
import com.project.ecommerce.inventory.repository.OrderRepository;
import com.project.ecommerce.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order createOrder(OrderRequestDto request){
        Order order = new Order();
        order.setCustomerName(request.customerName());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> items = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for(OrderItemRequestDto itemRequest : request.items()){

            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ProductNotFoundException(itemRequest.productId()));

            if(product.getQuantity() < itemRequest.quantity()){
                throw new InsufficientInventoryException(product.getId(), product.getQuantity(), itemRequest.quantity());
            }

            product.setQuantity(product.getQuantity() - itemRequest.quantity());

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);

            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.quantity()));

            totalAmount = totalAmount.add(itemTotal);

            items.add(orderItem);

        }
        order.setItems(items);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
