package br.com.jdrmservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.jdrmservices.model.ItemContrato;
import br.com.jdrmservices.session.TabelaItensContrato;

import static br.com.jdrmservices.util.Constants.VIEW_ITEM_CONTRATO;

@RestController
@RequestMapping("/itenscontrato")
public class ItensContratoController {

	@Autowired
	private TabelaItensContrato tabelaItensContrato;
	
	@PostMapping
	public ModelAndView adicionarItem(ItemContrato itemContrato) {
		ModelAndView mv = new ModelAndView(VIEW_ITEM_CONTRATO);
		
		tabelaItensContrato.adicionarItem(itemContrato);
		mv.addObject("itens", tabelaItensContrato.getItens());
		
		return mv;
	}
	
	@DeleteMapping("/{index}")
	public ModelAndView removerItem(@PathVariable int index) {
		ModelAndView mv = new ModelAndView(VIEW_ITEM_CONTRATO);
		
		tabelaItensContrato.removerItem(index);
		mv.addObject("itens", tabelaItensContrato.getItens());
		
		return mv;
	}
}