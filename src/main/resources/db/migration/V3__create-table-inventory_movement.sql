create table inventory_movement(
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    type VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    created_at DATETIME NOT NULL,

    PRIMARY KEY(id),

    INDEX idx_inventory_movement_product (product_id),
    CONSTRAINT fk_inventory_movement_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
);