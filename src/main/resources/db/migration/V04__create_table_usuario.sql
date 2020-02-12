CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `codigo_grupo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK55cw96aj2dhpy1pn9as3ktda3` (`codigo_grupo`),
  CONSTRAINT `FK55cw96aj2dhpy1pn9as3ktda3` FOREIGN KEY (`codigo_grupo`) REFERENCES `grupo` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO usuario (codigo, ativo, email, nome, senha, codigo_grupo) VALUES (1, 1, "admin@localhost", "ADMINISTRADOR", "$2a$10$KQsHcP24ueTabfEfU1oWKeNSHIXwNAHWfkUbbSczS.RXXkjYfcG32", 1);