CREATE TABLE IF NOT EXISTS pedido (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_produto int NOT NULL,
    id_usuario int NOT NULL,
    preco float NOT NULL,
    quantidade int NOT NULL,
    finalizado bit NOT NULL,
    FOREIGN KEY(id_produto) REFERENCES produto(id) ON UPDATE CASCADE ON DELETE,
    FOREIGN KEY(id_usuario) REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE
);