package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.dto.ProductCreateDto;
import com.project.ecommerce.inventory.dto.ProductUpdateDto;
import com.project.ecommerce.inventory.entity.Product;
import com.project.ecommerce.inventory.exception.ProductNotFoundException;
import com.project.ecommerce.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    @Transactional
    public Product saveProduct (ProductCreateDto dto){
        Product product = new Product(dto);

        return repository.save(product);
    }

    public Page<Product> getAllProducts (Pageable page){
        return repository.findAllByIsActiveTrue(page);
    }

    public Page<Product> getProductsByName(String productName, Pageable page){
        return repository.findByProductNameContainingIgnoreCaseAndIsActiveTrue(productName, page);
    }

    public Product getProductsById(Long productId){
        return repository.findById(productId).
                orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Transactional
    public Product updateProduct(ProductUpdateDto data){
        var product = repository.getReferenceById(data.id());
        product.updateProduct(data);
        return product;
    }

    @Transactional
    public void deleteProduct(Long id){
        var product = repository.getReferenceById(id);
        product.deleteProduct();
    }

}
