package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.entity.MovementType;
import com.project.ecommerce.inventory.entity.Product;
import com.project.ecommerce.inventory.repository.InventoryMovementRepository;
import com.project.ecommerce.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryMovementServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private InventoryMovementRepository inventoryRepository;

    @InjectMocks
    private InventoryMovementService service;

    @Test
    void shouldIncreaseInventoryAndSaveMovement() {
        // ARRANGE
        Long productId = 1L;
        int quantity = 5;

        Product product = new Product();
        product.setId(productId);
        product.setProductName("Vestido Midi");
        product.setQuantity(10);
        product.setPrice(new BigDecimal("199.99"));

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        // ACT
        Product result = service.entry(productId, quantity);

        // ASSERT

        // estoque aumentou
        assertEquals(15, result.getQuantity());

        // movement salvo
        verify(inventoryRepository, times(1))
                .save(argThat(movement ->
                        movement.getType() == MovementType.ENTRY &&
                                movement.getQuantity() == quantity &&
                                movement.getProduct().equals(product)
                ));

        // retorno correto
        assertNotNull(result);
    }
}