package com.project.ecommerce.inventory.repository;

import com.project.ecommerce.inventory.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
}
