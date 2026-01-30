package com.project.ecommerce.inventory.controller;

import com.project.ecommerce.inventory.dto.ProductCreateDto;
import com.project.ecommerce.inventory.dto.ProductResponseDto;
import com.project.ecommerce.inventory.dto.ProductUpdateDto;
import com.project.ecommerce.inventory.entity.Product;
import com.project.ecommerce.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody @Valid ProductCreateDto product, UriComponentsBuilder uriBuilder){
        Product savedProduct = productService.saveProduct(product);

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProductResponseDto(savedProduct));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var page = productService.getAllProducts(pageable)
                .map(ProductResponseDto::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/name")
    public ResponseEntity<Page<ProductResponseDto>> getProductsByName(@PageableDefault(size = 10, sort = {"id"})
                                                          @RequestParam String productName, Pageable page){
        var product = productService.getProductsByName(productName, page)
                .map(ProductResponseDto::new);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductsById(@PathVariable Long id){

        Product products = productService.getProductsById(id);
        return ResponseEntity.ok(new ProductResponseDto(products));
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid ProductUpdateDto product){

        Product updatedProduct = productService.updateProduct(product);

        return ResponseEntity.ok(new ProductResponseDto(updatedProduct));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
