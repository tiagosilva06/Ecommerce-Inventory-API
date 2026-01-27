package com.project.ecommerce.inventory.controller;

import com.project.ecommerce.inventory.dto.ProductCreateDto;
import com.project.ecommerce.inventory.dto.ProductResponseDto;
import com.project.ecommerce.inventory.dto.ProductUpdateDto;
import com.project.ecommerce.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<ProductResponseDto> getAllProducts(@PageableDefault(size = 10, sort = {"id"}) Pageable page) {
        return productService.getAllProducts(page)
                .map(ProductResponseDto::new);

    }

    @GetMapping("/name")
    public Page<ProductResponseDto> getProductsByName(@PageableDefault(size = 10, sort = {"id"})
                                                          @RequestParam String productName, Pageable page){
        return productService.getProductsByName(productName, page)
                .map(ProductResponseDto::new);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<ProductResponseDto>> getProductsById(@PathVariable Long id){

        List<ProductResponseDto> products = productService.getProductsById(id).stream().map(ProductResponseDto::new)
                .toList();
        return ResponseEntity.ok(products);
    }

    @PutMapping
    public void updateProduct(@RequestBody @Valid ProductUpdateDto product){
        productService.updateProduct(product);
    }


    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
