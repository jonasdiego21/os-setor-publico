package br.com.jdrmservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.model.ItemContrato;

@Repository
public interface ItensContrato extends JpaRepository<ItemContrato, Long> {

}
