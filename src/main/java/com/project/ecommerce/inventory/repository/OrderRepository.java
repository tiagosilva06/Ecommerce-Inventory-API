package com.project.ecommerce.inventory.repository;

import com.project.ecommerce.inventory.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
