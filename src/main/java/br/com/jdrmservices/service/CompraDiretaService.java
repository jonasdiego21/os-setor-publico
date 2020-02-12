package br.com.jdrmservices.service;

import static br.com.jdrmservices.util.Constants.INFORMACOES_JA_CADASTRADAS;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.exception.GlobalException;
import br.com.jdrmservices.model.CompraDireta;
import br.com.jdrmservices.repository.ComprasDiretas;

@Service
public class CompraDiretaService {

	@Autowired
	private ComprasDiretas comprasDiretas;
	
	@Transactional
	public void cadastrar(CompraDireta compradireta) {
		Optional<CompraDireta> optional = comprasDiretas.findByNumeroIgnoreCase(compradireta.getNumero());
		
		if(compradireta.isNovo() && optional.isPresent()) {
			throw new GlobalException(INFORMACOES_JA_CADASTRADAS);
		}
		
		comprasDiretas.saveAndFlush(compradireta);
	}
	
	@Transactional
	public void excluir(CompraDireta compradireta) {
		comprasDiretas.delete(compradireta);
	}	
}