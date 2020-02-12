package br.com.jdrmservices.repository.helper.contrato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdrmservices.model.Contrato;
import br.com.jdrmservices.repository.filter.ContratoFilter;

public interface ContratosQueries {
	public Page<Contrato> filtrar(ContratoFilter filtro, Pageable pageable);
}