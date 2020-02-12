package br.com.jdrmservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.model.Cidade;

@Repository
public interface Cidades extends JpaRepository<Cidade, Long> {
	public List<Cidade> findByEstadoCodigo(Long codigoEstado);	
}