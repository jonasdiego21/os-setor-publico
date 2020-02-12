package br.com.jdrmservices.repository.helper.fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.jdrmservices.model.Fornecedor;
import br.com.jdrmservices.repository.filter.FornecedorFilter;

public class FornecedoresImpl implements FornecedoresQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable) {
		
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Fornecedor.class);
		
		criteria.addOrder(Order.asc("nome"));
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(FornecedorFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Fornecedor.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(FornecedorFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(filtro.getTipoPessoa() != null) {
				criteria.add(Restrictions.eq("tipoPessoa", filtro.getTipoPessoa()));
			}
			
			if(!StringUtils.isEmpty(filtro.getCpfOuCnpj())) {
				criteria.add(Restrictions.ilike("cpfOuCnpj", filtro.getCpfOuCnpj().replaceAll("\\.|-|/", ""), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("fixo", filtro.getFixo(), MatchMode.ANYWHERE));
			}
			
			if(!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("telefone", filtro.getTelefone(), MatchMode.ANYWHERE));
			}
		}
	}
}