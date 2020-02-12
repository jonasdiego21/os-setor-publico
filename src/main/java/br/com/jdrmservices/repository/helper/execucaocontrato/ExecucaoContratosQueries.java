package br.com.jdrmservices.repository.helper.execucaocontrato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdrmservices.model.ExecucaoContrato;
import br.com.jdrmservices.repository.filter.ExecucaoContratoFilter;

public interface ExecucaoContratosQueries {
	public Page<ExecucaoContrato> filtrar(ExecucaoContratoFilter filtro, Pageable pageable);
}