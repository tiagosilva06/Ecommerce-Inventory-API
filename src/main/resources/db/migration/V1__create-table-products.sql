create table products(
    id BIGINT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(150) NOT NULL,
    color VARCHAR(50) NOT NULL,
    product_size VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    is_active BOOLEAN NOT NULL,

    PRIMARY KEY(id)
);