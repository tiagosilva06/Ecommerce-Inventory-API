package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.repository.InventoryMovementRepository;
import com.project.ecommerce.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryMovementService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryMovementRepository inventoryRepository;


}
