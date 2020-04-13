package br.com.jdrmservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.model.Contrato;
import br.com.jdrmservices.repository.helper.contrato.ContratosQueries;

@Repository
public interface Contratos extends JpaRepository<Contrato, Long>, ContratosQueries {
	public Optional<Contrato> findByNumeroIgnoreCase(String numero);
	public List<Contrato> findAllByOrderByNumeroAsc();
	public ResponseEntity<?> findByFornecedor(Long codigo);
}