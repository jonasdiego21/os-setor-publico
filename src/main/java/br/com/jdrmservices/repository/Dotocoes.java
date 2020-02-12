package br.com.jdrmservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jdrmservices.model.Dotacao;
import br.com.jdrmservices.repository.helper.dotacao.DotacoesQueries;

@Repository
public interface Dotocoes extends JpaRepository<Dotacao, Long>, DotacoesQueries {

}
