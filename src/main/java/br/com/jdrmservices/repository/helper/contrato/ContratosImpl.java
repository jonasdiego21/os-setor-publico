package br.com.jdrmservices.repository.helper.contrato;

import java.math.BigDecimal;

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

import br.com.jdrmservices.model.Contrato;
import br.com.jdrmservices.repository.filter.ContratoFilter;

public class ContratosImpl implements ContratosQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Contrato> filtrar(ContratoFilter filtro, Pageable pageable) {
		
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Contrato.class);
		
		criteria.addOrder(Order.asc("numero"));
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(ContratoFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Contrato.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(ContratoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(filtro.getDataInicio() != null) {
				criteria.add(Restrictions.ge("dataInicio", filtro.getDataInicio()));
			}
			
			if(filtro.getDataTermino() != null) {
				criteria.add(Restrictions.le("dataTermino", filtro.getDataTermino()));
			}
			
			if(!StringUtils.isEmpty(filtro.getNumero())) {
				criteria.add(Restrictions.ilike("numero", filtro.getNumero(), MatchMode.EXACT));
			}
			
			if(!StringUtils.isEmpty(filtro.getNumeroLicitacao())) {
				criteria.add(Restrictions.ilike("numeroLicitacao", filtro.getNumeroLicitacao(), MatchMode.EXACT));
			}
			
			if(!StringUtils.isEmpty(filtro.getObjeto())) {
				criteria.add(Restrictions.ilike("objeto", filtro.getObjeto(), MatchMode.ANYWHERE));
			}
			
			if(filtro.getTipoMaterial() != null) {
				criteria.add(Restrictions.eq("tipoMaterial", filtro.getTipoMaterial()));
			}
			
			if(filtro.getValorContrato() != null) {
				criteria.add(Restrictions.eq("valorContrato", filtro.getValorContrato()));
			}
			
			if(filtro.getFornecedor() != null) {
				criteria.add(Restrictions.eq("fornecedor", filtro.getFornecedor()));
			}
			
			if(filtro.getSecretaria() != null) {
				criteria.add(Restrictions.eq("secretaria", filtro.getSecretaria()));
			}
		}
	}
}