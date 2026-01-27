package com.project.ecommerce.inventory.repository;

import com.project.ecommerce.inventory.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByProductNameContainingIgnoreCaseAndIsActiveTrue(String productName, Pageable page);

    Page<Product> findAllByIsActiveTrue(Pageable page);
}
