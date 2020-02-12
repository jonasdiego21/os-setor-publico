package br.com.jdrmservices.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdrmservices.model.ItemCompraDireta;
import br.com.jdrmservices.repository.ItensCompraDireta;

@Service
public class ItemCompraDiretaService {

	@Autowired
	private ItensCompraDireta itensCompraDireta;
	
	@Transactional
	public void cadastrar(ItemCompraDireta itemCompraDireta) {
		itensCompraDireta.saveAndFlush(itemCompraDireta);
	}
	
	@Transactional
	public void excluir(ItemCompraDireta itemCompraDireta) {
		itensCompraDireta.delete(itemCompraDireta);
	}
}
