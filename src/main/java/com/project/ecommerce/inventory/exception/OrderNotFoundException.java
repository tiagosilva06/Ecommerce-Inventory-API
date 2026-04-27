package com.project.ecommerce.inventory.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super("Order Not Found with id: " + orderId);
    }
}
