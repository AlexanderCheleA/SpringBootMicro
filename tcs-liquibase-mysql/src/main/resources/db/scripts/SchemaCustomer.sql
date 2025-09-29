--liquibase formatted sql
--changeset aChele:create_persons_table
CREATE TABLE persons (
    personId BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    identification VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(150) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

--changeset aChele:create_customers_table
CREATE TABLE customers (
    clientId BIGINT AUTO_INCREMENT PRIMARY KEY,
    personId BIGINT NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    status BOOLEAN NOT NULL,
    CONSTRAINT fk_person FOREIGN KEY(personId) REFERENCES persons(personId)
);
