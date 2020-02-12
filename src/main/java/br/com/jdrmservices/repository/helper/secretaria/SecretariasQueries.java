package br.com.jdrmservices.repository.helper.secretaria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jdrmservices.model.Secretaria;
import br.com.jdrmservices.repository.filter.SecretariaFilter;

public interface SecretariasQueries {
	public Page<Secretaria> filtrar(SecretariaFilter filtro, Pageable pageable);
}