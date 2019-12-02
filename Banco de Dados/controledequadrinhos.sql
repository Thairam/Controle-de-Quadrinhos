DROP DATABASE IF EXISTS controledequadrinhos;
CREATE DATABASE controledequadrinhos;
USE controledequadrinhos;
/* SET default_storage_engine = INNODB; */

CREATE TABLE Colecao(
	id 				integer AUTO_INCREMENT,
    nome 			varchar(30) NOT NULL,
    tam_total	integer,
    
    PRIMARY KEY(id)
);

INSERT INTO Colecao(id, nome, tam_total) VALUES (1, 'Especial Batman', 10);
INSERT INTO Colecao(id, nome, tam_total) VALUES (2, 'Poderoso Wolverine', 5);
INSERT INTO Colecao(id, nome, tam_total) VALUES (3, 'Espetacular Homem Aranha', 35);

CREATE TABLE Endereco(
  id	  integer AUTO_INCREMENT,	
  cep     varchar(10) UNIQUE,
  rua     varchar(50) NOT NULL,
  bairro  varchar(30),
  cidade  varchar(30),
  uf      char(2),
  
  PRIMARY KEY (id)
);

INSERT INTO Endereco (id, cep, rua, bairro, cidade, uf) 
VALUES (1, '58117000', 'Joao Jeronimo da Costa', 'Bela Vista', 'Lagoa Seca', 'PB');

INSERT INTO Endereco (id, cep, rua, bairro, cidade, uf) 
VALUES (2, '58400807', 'Rua de Adson', 'Novo Cruzeiro', 'Campina Grande', 'PB');

INSERT INTO Endereco (id, cep, rua, bairro, cidade, uf) 
VALUES (3, '59200000', 'Rua de Ramom', 'Universitario', 'Campina Grande', 'PB');

CREATE TABLE Quadrinho(
	id 					integer AUTO_INCREMENT,
    id_colecao 			integer,
    nome 				varchar(30) NOT NULL,
    valor 				double NOT NULL,
    editora 			varchar(30) NOT NULL,
    isbn 				varchar(30) UNIQUE,
    versao_fisica 		bool,
    versao_digital 		bool,
    edicao 				varchar(30),
    genero 				varchar(30),
    disponibilidadeF 	bool DEFAULT TRUE,
    disponibilidadeD 	bool DEFAULT TRUE,
    curiosidade 		varchar(100) DEFAULT NULL,
    nota 				float DEFAULT 0.0,
    recomendavel		bool DEFAULT TRUE,
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_colecao) REFERENCES Colecao(id) ON DELETE SET NULL
);

INSERT INTO Quadrinho
	(id, id_colecao, nome, valor, editora, isbn, versao_fisica, versao_digital, edicao, genero, disponibilidadeF, disponibilidadeD, curiosidade, nota, recomendavel)
VALUES
    (1, 1, 'Batman', 15.50, 'Marvel', '0008589064', true, true, 'Edicao Batman', 'ação', true, true,'', 9, true);

INSERT INTO Quadrinho
	(id, id_colecao, nome, valor, editora, isbn, versao_fisica, versao_digital, edicao, genero, disponibilidadeF, disponibilidadeD, curiosidade, nota, recomendavel)
VALUES
    (2, 1, 'Homem Aranha', 9.75, 'Marvel', '0005821154780', false, true, 'Edicao spiderman', 'ação', false, true,'Inpirou o filme', 10, true);

INSERT INTO Quadrinho
	(id, id_colecao, nome, valor, editora, isbn, versao_fisica, versao_digital, edicao, genero, disponibilidadeF, disponibilidadeD, curiosidade, nota, recomendavel)
VALUES
    (3, 2, 'Wolverine o temido', 9.90, 'DC Comics', '1000088897', true, false, 'Edicao Batman', 'ação',  true, false, 'Inpirou o filme', 10, true);

CREATE TABLE Amigo(
	id 				integer AUTO_INCREMENT,
    nome 			varchar(30),
    data_nascimento date,
    cpf 			varchar(30) UNIQUE NOT NULL,
    id_endereco 	integer,
    fone 			varchar(20),
    email 			varchar(50),
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_endereco) REFERENCES Endereco(id) ON DELETE SET NULL
);

INSERT INTO Amigo(id, nome, data_nascimento, cpf, id_endereco, fone, email)
VALUES (1, 'Thairam', '1994/10/25', '123.456.789-00', 1, '083 99965-1044', 'thayram01@gmail.com');

INSERT INTO Amigo(id, nome, data_nascimento, cpf, id_endereco, fone, email) 
VALUES (2, 'Adson', '1984/06/13', '222.222.222-22', 2, '083 98898-1245', 'agnsoft@hotmail.com');

INSERT INTO Amigo(id, nome, data_nascimento, cpf, id_endereco, fone, email) 
VALUES (3, 'Ramon', '1996/01/01', '333.222.332-99', 3, '081 99974-0104', 'rrsalescc@gmail.com');

CREATE TABLE Emprestimo(
	id 				integer AUTO_INCREMENT,
    id_amigo		integer,
    data_emprestimo date,
    data_devolucao 	date,
    estado 			enum('ABERTO', 'FECHADO') DEFAULT 'ABERTO',
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_amigo) REFERENCES Amigo(id)
);
/*
INSERT INTO Emprestimo(id, id_amigo, data_emprestimo, data_devolucao, estado)
VALUES (1, 1, '2019/11/30', '2019/12/30', 'ABERTO');

INSERT INTO Emprestimo(id, id_amigo, data_emprestimo, data_devolucao, estado)
VALUES (2, 2, '2019/11/30', '2019/12/05', 'ABERTO');

INSERT INTO Emprestimo(id, id_amigo, data_emprestimo, data_devolucao, estado)
VALUES (3, 3, '2019/11/30', '2019/12/15', 'ABERTO');
*/
CREATE TABLE Item(
	id 				integer AUTO_INCREMENT,
    id_emprestimo 	integer,
    id_quadrinho 	integer,
    versao			enum('F','D'),
    
    PRIMARY KEY(id),
    FOREIGN KEY(id_emprestimo) REFERENCES Emprestimo(id) ON DELETE SET NULL,
    FOREIGN KEY(id_quadrinho) REFERENCES Quadrinho(id)   
);
/*
INSERT INTO Item(id, id_emprestimo, id_quadrinho, versao)
VALUES (1, 1, 1, 'F');

INSERT INTO Item(id, id_emprestimo, id_quadrinho, versao)
VALUES (2, 1, 3, 'D');

INSERT INTO Item(id, id_emprestimo, id_quadrinho, versao)
VALUES (3, 2, 1, 'D');

INSERT INTO Item(id, id_emprestimo, id_quadrinho, versao)
VALUES (4, 3, 2, 'F');
*/
DELIMITER $

CREATE 
TRIGGER upd_versoes
AFTER INSERT
ON item
FOR EACH ROW
BEGIN	
	IF (NEW.versao = "F") THEN
		UPDATE quadrinho SET disponibilidadeF = false WHERE id  = NEW.id_quadrinho;
	END IF;
	IF (NEW.versao = "D") THEN
		UPDATE quadrinho SET disponibilidadeD = false WHERE id  = NEW.id_quadrinho;
	END IF;
END$

CREATE 
TRIGGER del_emprestimo
BEFORE DELETE
ON emprestimo
FOR EACH ROW
BEGIN	
	UPDATE item SET id_quadrinho = null WHERE id = item.id;
    DELETE FROM item where id = item.id;
END$
DELIMITER ;