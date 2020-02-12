package br.com.jdrmservices.repository.helper.compradireta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdrmservices.model.CompraDireta;
import br.com.jdrmservices.repository.filter.CompraDiretaFilter;

public interface ComprasDiretasQueries {
	public Page<CompraDireta> filtrar(CompraDiretaFilter filtro, Pageable pageable);
}