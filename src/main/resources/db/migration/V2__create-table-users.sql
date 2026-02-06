create table users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    login VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,

    PRIMARY KEY(id)
);