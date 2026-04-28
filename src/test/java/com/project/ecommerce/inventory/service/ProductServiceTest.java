package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.dto.ProductCreateDto;
import com.project.ecommerce.inventory.entity.Product;
import com.project.ecommerce.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldCreateProductSuccessfully() {
        // ARRANGE
        ProductCreateDto dto = new ProductCreateDto(
                "Vestido Midi",
                "Preto",
                "M",
                10,
                new BigDecimal("199.99")
        );

        Product product = new Product(dto);

        when(repository.save(any(Product.class))).thenReturn(product);

        // ACT
        Product result = productService.saveProduct(dto);

        // ASSERT
        assertNotNull(result);
        assertEquals("Vestido Midi", result.getProductName());
        assertEquals(10, result.getQuantity());
        assertEquals(new BigDecimal("199.99"), result.getPrice());

        verify(repository, times(1)).save(any(Product.class));
    }
}