CREATE TABLE pedido (

    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_produto int NOT NULL,
    id_usuario int NOT NULL,
    preco float NOT NULL,
    produto varchar(255) NOT NULL,
    quantidade int NOT NULL,
    usuario varchar(255) NOT NULL,

);