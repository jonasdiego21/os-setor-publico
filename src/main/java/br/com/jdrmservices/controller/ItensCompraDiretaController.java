package br.com.jdrmservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.jdrmservices.model.ItemCompraDireta;
import br.com.jdrmservices.session.TabelaItensCompraDireta;

import static br.com.jdrmservices.util.Constants.VIEW_ITEM_COMPRA_DIRETA;

@RestController
@RequestMapping("/itenscompradireta")
public class ItensCompraDiretaController {

	@Autowired
	private TabelaItensCompraDireta tabelaItensCompraDireta;
	
	@PostMapping
	public ModelAndView adicionarItem(ItemCompraDireta itemCompraDireta) {
		ModelAndView mv = new ModelAndView(VIEW_ITEM_COMPRA_DIRETA);
		
		tabelaItensCompraDireta.adicionarItem(itemCompraDireta);
		mv.addObject("itens", tabelaItensCompraDireta.getItens());
		
		return mv;
	}
	
	@DeleteMapping
	public ModelAndView removerItem(ItemCompraDireta itemCompraDireta) {
		ModelAndView mv = new ModelAndView(VIEW_ITEM_COMPRA_DIRETA);
		
		tabelaItensCompraDireta.removerItem(itemCompraDireta);
		mv.addObject("itens", tabelaItensCompraDireta.getItens());
		
		return mv;
	}
}
