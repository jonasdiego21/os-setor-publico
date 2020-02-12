package br.com.jdrmservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.model.Secretaria;
import br.com.jdrmservices.repository.helper.secretaria.SecretariasQueries;

@Repository
public interface Secretarias extends JpaRepository<Secretaria, Long>, SecretariasQueries {
	public Optional<Secretaria> findByNomeIgnoreCase(String nome);
	public List<Secretaria> findAllByOrderByNomeAsc();
}