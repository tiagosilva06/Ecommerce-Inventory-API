package com.project.ecommerce.inventory.infraestructure.repository;

import com.project.ecommerce.inventory.infraestructure.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
