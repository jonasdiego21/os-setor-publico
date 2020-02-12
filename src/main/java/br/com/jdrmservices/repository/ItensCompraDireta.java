package br.com.jdrmservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.model.ItemCompraDireta;

@Repository
public interface ItensCompraDireta extends JpaRepository<ItemCompraDireta, Long> {

}
