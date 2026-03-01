package com.project.ecommerce.inventory.exception;

public class InsufficientInventoryException extends RuntimeException {

    public InsufficientInventoryException (Long productId, int available, int requested){

        super("Insufficient inventory for product id: " + productId +
                ". Available: " + available +
                ".Requested: " + requested
        );
    }
}
