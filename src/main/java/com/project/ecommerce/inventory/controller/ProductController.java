package com.project.ecommerce.inventory.controller;

import com.project.ecommerce.inventory.dto.ProductDto;
import com.project.ecommerce.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestBody @Valid ProductDto product){
        productService.saveProduct(product);
        return ResponseEntity.ok().build();
    }
}
