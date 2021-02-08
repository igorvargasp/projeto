CREATE TABLE  produto (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome varchar(25) NOT NULL,
    preco decimal NOT NULL,
    quantidade integer NOT NULL,
    disponivel tinyint NOT NULL,
);