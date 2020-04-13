package br.com.jdrmservices.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.model.ExecucaoContrato;
import br.com.jdrmservices.repository.ExecucaoContratos;

@Service
public class ExecucaoContratoService {

	@Autowired
	private ExecucaoContratos execucaoContratos;
	
	@Transactional
	public void cadastrar(ExecucaoContrato execucaoContrato) {
		execucaoContratos.saveAndFlush(execucaoContrato);
	}
	
	@Transactional
	public void excluir(ExecucaoContrato execucaoContrato) {
		execucaoContratos.delete(execucaoContrato);
	}	
}