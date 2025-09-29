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
