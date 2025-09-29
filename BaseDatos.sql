
CREATE TABLE accounts (
    accountNumber BIGINT AUTO_INCREMENT PRIMARY KEY,
    accountType VARCHAR(50) NOT NULL,
    initialBalance DECIMAL(19,2) NOT NULL,
    status BOOLEAN NOT NULL,
    clientId BIGINT NOT NULL
);

CREATE TABLE transactions (
    transactionId BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATETIME NOT NULL,
    transactionType VARCHAR(50) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    balance DECIMAL(19,2) NOT NULL,
    accountNumber BIGINT NOT NULL,
    CONSTRAINT fk_account_transaction FOREIGN KEY (accountNumber) REFERENCES accounts(accountNumber)
);

CREATE TABLE persons (
    personId BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    age INT NOT NULL,
    identification VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(150) NOT NULL,
    phone VARCHAR(15) NOT NULL
);

CREATE TABLE customers (
    clientId BIGINT AUTO_INCREMENT PRIMARY KEY,
    personId BIGINT NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    status BOOLEAN NOT NULL,
    CONSTRAINT fk_person FOREIGN KEY(personId) REFERENCES persons(personId)
);

INSERT INTO persons (name, gender, age, identification, address, phone) VALUES
('Jose Lema', 'Male', 35, '001', 'Otavalo sn y principal', '098254785'),
('Marianela Montalvo', 'Female', 32, '002', 'Amazonas y NNUU', '097548965'),
('Juan Osorio', 'Male', 40, '003', '13 junio y Equinoccial', '098874587'),
('Alexander Chele', 'Male', 26, '005', 'Ecuador test data', '09874587412');

INSERT INTO customers (personId, password, status) VALUES
(1, '1234', TRUE),
(2, '5678', TRUE),
(3, '1245', TRUE),
(4, '2222', TRUE);

INSERT INTO accounts (accountNumber, accountType, initialBalance, status, clientId) VALUES
(225487, 'Corriente', 100.00, TRUE, 1),
(496825, 'Ahorros', 540.00, TRUE, 2),
(478758, 'Ahorro', 2000.00, TRUE, 3),
(495878, 'Ahorros', 0.00, TRUE, 4);

INSERT INTO transactions (date, transactionType, amount, balance, accountNumber) VALUES
('2022-02-10 10:00:00', 'Deposito', 600.00, 700.00, 225487),
('2022-02-08 09:00:00', 'Retiro', -540.00, 0.00, 496825),
('2022-02-07 08:00:00', 'Retiro', -575.00, 1425.00, 478758),
('2022-02-07 08:30:00', 'Deposito', 150.00, 150.00, 495878);
