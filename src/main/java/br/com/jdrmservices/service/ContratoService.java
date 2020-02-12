package br.com.jdrmservices.service;

import static br.com.jdrmservices.util.Constants.INFORMACOES_JA_CADASTRADAS;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.exception.GlobalException;
import br.com.jdrmservices.model.Contrato;
import br.com.jdrmservices.repository.Contratos;

@Service
public class ContratoService {

	@Autowired
	private Contratos contratos;
	
	@Transactional
	public void cadastrar(Contrato contrato) {
		Optional<Contrato> optional = contratos.findByNumeroIgnoreCase(contrato.getNumero());
		
		if(contrato.isNovo() && optional.isPresent()) {
			throw new GlobalException(INFORMACOES_JA_CADASTRADAS);
		}
		
		contratos.saveAndFlush(contrato);
	}
	
	@Transactional
	public void excluir(Contrato contrato) {
		contratos.delete(contrato);
	}	
}