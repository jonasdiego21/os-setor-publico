package br.com.jdrmservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.model.ExecucaoContrato;
import br.com.jdrmservices.repository.helper.execucaocontrato.ExecucaoContratosQueries;

@Repository
public interface ExecucaoContratos extends JpaRepository<ExecucaoContrato, Long>, ExecucaoContratosQueries {
	
}