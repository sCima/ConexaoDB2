DROP SEQUENCE usuario_sequence;
DROP TRIGGER tr_insert_id_usuario;
DROP TABLE usuarios;

CREATE TABLE usuarios (
	id integer PRIMARY KEY NOT NULL,
	nome varchar(45) NOT NULL,
	email varchar(45) NOT NULL,
	senha varchar(45) NOT NULL,
	data Date NOT NULL
)

CREATE SEQUENCE usuario_sequence START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER tr_insert_id_usuario
BEFORE INSERT ON USUARIOS FOR EACH ROW
BEGIN
SELECT usuario_sequence.INTERVAL
INTO :NEW.id
FROM DUAL;
END;
/
COMMIT;

-- Insere na tabela usuários os valores passados respectivamente
INSERT INTO usuarios(nome,email,senha,data) VALUES ('Rodolfo', 'rm99748@fiap.com.br', '123456', '13-mar-2003');

-- Seleciona todas as informações de todos os usuários
SELECT * from usuarios