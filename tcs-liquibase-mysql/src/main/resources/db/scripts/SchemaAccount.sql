--liquibase formatted sql
--changeset aChele:create_accounts_table
CREATE TABLE accounts (
    accountNumber BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountType VARCHAR(50) NOT NULL,
    initialBalance DECIMAL(19,2) NOT NULL,
    status BOOLEAN NOT NULL,
    clientId BIGINT NOT NULL
);

--changeset aChele:create_transactions_table
CREATE TABLE transactions (
    transactionId BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATETIME NOT NULL,
    transactionType VARCHAR(50) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    balance DECIMAL(19,2) NOT NULL,
    accountNumber BIGINT NOT NULL,
    CONSTRAINT fk_account_transaction FOREIGN KEY (accountNumber) REFERENCES accounts(accountNumber)
);
