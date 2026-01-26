package com.project.ecommerce.inventory.entity;

import com.project.ecommerce.inventory.dto.ProductCreateDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;
    private String color;

    @Column(name = "product_size")
    private String size;

    private int quantity;
    private BigDecimal price;

    @Column(name = "is_active")
    private Boolean isActive;

    public Product (ProductCreateDto dto){
        this.productName = dto.productName();
        this.color = dto.color();
        this.size = dto.size();
        this.quantity = dto.quantity();
        this.price = dto.price();
        this.isActive = true;
    }

}
