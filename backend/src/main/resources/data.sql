-- Use backticks around `user` (reserved word in MySQL)

-- Users (adjust column names if different)
INSERT IGNORE INTO `user` (username, password) VALUES
('username','password');
INSERT IGNORE INTO `user` (username, password) VALUES
('admin','admin123');

-- Customers: looks like (id, address, name)
INSERT IGNORE INTO customer (id, address, name) VALUES
('C001','Panadura','Danapala'),
('C002','Matara','Gunapala'),
('C003','Galle','Somapala'),
('C004','Galle','Siripala'),
('C005','Panadura','Jinadasa'),
('C006','Kalutara','Sepalika'),
('C007','Ambalangoda','Jinasena'),
('C008','Baddegama','Somadasa'),
('C009','Moratuwa','Danasiri'),
('C010','Kandy','Somasiri');

-- Items: looks like (code, qty, description, price)
INSERT IGNORE INTO items (code, quantity, description, price) VALUES
('P001',3000,'Keerisamba Retail',105.00),
('P002',200,'Keerisamba 5Kg ',525.00),
('P003',36,'Keerisamba 10Kg',995.00),
('P004',36,'Keerisamba 50Kg',4100.00),
('P005',6000,'Red Raw Rice',60.00),
('P006',300,'Red Raw Rice 10Kg Pack',560.00),
('P007',80,'Red Raw Rice 50Kg Pack',5230.00),
('P008',130,'White Raw Rice 5Kg Pack',275.00),
('P009',13,'White Raw Rice 50Kg Pack',2600.00),
('P010',83,'Wattana Dhal 500g',90.00),
('P011',40,'Wattana Dhal 1Kg',170.00),
('P012',89,'Mysoor Dhal 500g',90.00),
('P013',59,'Mysoor Dhal 1Kg',180.00),
('P014',39,'Orient Green Gram 500g',118.00),
('P015',12,'Orient Green Gram 1Kg',220.00),
('P016',93,'Anchor F/C Milk powder 450g',220.00),
('P017',40,'Anchor F/C Milk powder 1Kg',580.00),
('P018',33,'Anchor N/F Milk powder 1Kg',560.00),
('P019',33,'Milo Packet 400g',240.00),
('P020',40,'Lipton Ceylon Tea 100g',107.00);
