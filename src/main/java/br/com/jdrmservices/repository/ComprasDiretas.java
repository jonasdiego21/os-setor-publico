package br.com.jdrmservices.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.model.CompraDireta;
import br.com.jdrmservices.repository.helper.compradireta.ComprasDiretasQueries;

@Repository
public interface ComprasDiretas extends JpaRepository<CompraDireta, Long>, ComprasDiretasQueries {
	public List<CompraDireta> findByOrderByNumeroAsc();
	public Optional<CompraDireta> findByNumeroIgnoreCase(String numero);
}