package com.project.ecommerce.inventory.controller;

import com.project.ecommerce.inventory.dto.ProductCreateDto;
import com.project.ecommerce.inventory.dto.ProductResponseDto;
import com.project.ecommerce.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid ProductCreateDto product){
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {

        List<ProductResponseDto> products = productService.getAllProducts()
                .stream()
                .map(ProductResponseDto::new)
                .toList();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/name")
    public ResponseEntity<List<ProductResponseDto>> getProductsByName(@RequestParam @Valid String name){

        List<ProductResponseDto> products = productService.getProductsByName(name)
                .stream()
                .map(ProductResponseDto::new)
                .toList();

        return ResponseEntity.ok(products);
    }
}
