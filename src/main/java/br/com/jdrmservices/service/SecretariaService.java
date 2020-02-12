package br.com.jdrmservices.service;

import static br.com.jdrmservices.util.Constants.INFORMACOES_JA_CADASTRADAS;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.exception.GlobalException;
import br.com.jdrmservices.model.Secretaria;
import br.com.jdrmservices.repository.Secretarias;

@Service
public class SecretariaService {

	@Autowired
	private Secretarias secretarias;
	
	@Transactional
	public void cadastrar(Secretaria secretaria) {
		Optional<Secretaria> optional = secretarias.findByNomeIgnoreCase(secretaria.getNome());
		
		if(secretaria.isNovo() && optional.isPresent()) {
			throw new GlobalException(INFORMACOES_JA_CADASTRADAS);
		}
		
		secretarias.saveAndFlush(secretaria);
	}
	
	@Transactional
	public void excluir(Secretaria secretaria) {
		secretarias.delete(secretaria);
	}	
}