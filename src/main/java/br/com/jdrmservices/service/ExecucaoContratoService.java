package br.com.jdrmservices.service;

import static br.com.jdrmservices.util.Constants.INFORMACOES_JA_CADASTRADAS;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.exception.GlobalException;
import br.com.jdrmservices.model.ExecucaoContrato;
import br.com.jdrmservices.repository.ExecucaoContratos;

@Service
public class ExecucaoContratoService {

	@Autowired
	private ExecucaoContratos execucaoContratos;
	
	@Transactional
	public void cadastrar(ExecucaoContrato execucaoContrato) {
		Optional<ExecucaoContrato> optional = execucaoContratos.findByFornecedorIgnoreCase(execucaoContrato.getFornecedor());
		
		if(execucaoContrato.isNovo() && optional.isPresent()) {
			throw new GlobalException(INFORMACOES_JA_CADASTRADAS);
		}
		
		execucaoContratos.saveAndFlush(execucaoContrato);
	}
	
	@Transactional
	public void excluir(ExecucaoContrato execucaoContrato) {
		execucaoContratos.delete(execucaoContrato);
	}	
}