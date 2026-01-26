package com.project.ecommerce.inventory.service;

import com.project.ecommerce.inventory.dto.ProductCreateDto;
import com.project.ecommerce.inventory.entity.Product;
import com.project.ecommerce.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    @Transactional
    public void saveProduct (ProductCreateDto dto){
        Product product = new Product(dto);

        repository.save(product);
    }

    public List<Product> getAllProducts (){
        return repository.findAll();
    }

    public List<Product> getProductsByName(String name){
        return repository.findByProductNameContainingIgnoreCase(name);
    }

}
