package br.com.jdrmservices.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.model.ItemContrato;
import br.com.jdrmservices.repository.ItensContrato;

@Service
public class ItemContratoService {

	@Autowired
	private ItensContrato itensContrato;
	
	@Transactional
	public void cadastrar(ItemContrato itemContrato) {
		itensContrato.saveAndFlush(itemContrato);
	}
	
	@Transactional
	public void excluir(ItemContrato itemContrato) {
		itensContrato.delete(itemContrato);
	}
}
