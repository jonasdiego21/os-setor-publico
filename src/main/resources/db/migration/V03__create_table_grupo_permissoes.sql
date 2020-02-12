CREATE TABLE `grupo_permissoes` (
  `codigo_grupo` bigint(20) NOT NULL,
  `codigo_permissao` bigint(20) NOT NULL,
  KEY `FK7vlthmg3pq6l7pbl860gfg1c1` (`codigo_permissao`),
  KEY `FK7lo3mm9yfyjw4hb8bfdct0y44` (`codigo_grupo`),
  CONSTRAINT `FK7lo3mm9yfyjw4hb8bfdct0y44` FOREIGN KEY (`codigo_grupo`) REFERENCES `grupo` (`codigo`),
  CONSTRAINT `FK7vlthmg3pq6l7pbl860gfg1c1` FOREIGN KEY (`codigo_permissao`) REFERENCES `permissao` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;