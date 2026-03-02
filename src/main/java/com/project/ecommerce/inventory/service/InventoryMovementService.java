package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.entity.InventoryMovement;
import com.project.ecommerce.inventory.entity.MovementType;
import com.project.ecommerce.inventory.entity.Product;
import com.project.ecommerce.inventory.exception.ProductNotFoundException;
import com.project.ecommerce.inventory.repository.InventoryMovementRepository;
import com.project.ecommerce.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryMovementService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryMovementRepository inventoryRepository;

    @Transactional
    public Product entry (Long productId, int quantity){

       Product product = productRepository.findById(productId).
               orElseThrow(() -> new ProductNotFoundException(productId));

       product.increaseInventory(quantity);

       InventoryMovement movement = InventoryMovement.builder()
               .product(product)
               .type(MovementType.ENTRY)
               .quantity(quantity)
               .build();

       inventoryRepository.save(movement);
       return product;
    }

    @Transactional
    public Product exit (Long productId, int quantity){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        product.decreaseInventory(quantity);

        InventoryMovement movement = InventoryMovement.builder()
                .product(product)
                .type(MovementType.EXIT)
                .quantity(quantity)
                .build();

        inventoryRepository.save(movement);
        return product;
    }
}
