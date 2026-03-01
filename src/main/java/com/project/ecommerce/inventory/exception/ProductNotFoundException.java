package com.project.ecommerce.inventory.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super("Product Not Found with id: " + productId);
    }
}
