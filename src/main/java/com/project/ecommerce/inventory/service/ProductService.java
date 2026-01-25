package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.dto.ProductDto;
import com.project.ecommerce.inventory.infraestructure.entities.Product;
import com.project.ecommerce.inventory.infraestructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    @Transactional
    public void saveProduct (ProductDto dto){
        Product product = new Product(dto);

        repository.save(product);
    }
}
