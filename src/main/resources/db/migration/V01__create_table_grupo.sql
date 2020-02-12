CREATE TABLE `grupo` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO grupo (codigo, nome) values (1, 'ADMINISTRADOR');