package br.com.jdrmservices.controller;

import static br.com.jdrmservices.util.Constants.INFORMACOES_SALVAS_SUCESSO;
import static br.com.jdrmservices.util.Constants.VIEW_CONTRATO_NOVO;
import static br.com.jdrmservices.util.Constants.VIEW_CONTRATO_REDIRECT;
import static br.com.jdrmservices.util.Constants.VIEW_PESQUISAR_CONTRATO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jdrmservices.exception.GlobalException;
import br.com.jdrmservices.model.Contrato;
import br.com.jdrmservices.model.ItemContrato;
import br.com.jdrmservices.model.enumeration.TipoMaterial;
import br.com.jdrmservices.model.enumeration.UnidadeMedida;
import br.com.jdrmservices.repository.Contratos;
import br.com.jdrmservices.repository.Fornecedores;
import br.com.jdrmservices.repository.Secretarias;
import br.com.jdrmservices.repository.filter.ContratoFilter;
import br.com.jdrmservices.service.ContratoService;
import br.com.jdrmservices.session.TabelaItensContrato;

@RestController
@RequestMapping("/contratos")
public class ContratosController {

	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private Contratos contratos;
	
	@Autowired
	private Secretarias secretarias;

	@Autowired
	private Fornecedores fornecedores;
	
	@Autowired
	private TabelaItensContrato tabelaItensContrato;
	
	@GetMapping("/novo")
	public ModelAndView novo(Contrato contrato) {
		ModelAndView mv = new ModelAndView(VIEW_CONTRATO_NOVO);
		mv.addObject("contratos", contratos.findAllByOrderByNumeroAsc());
		mv.addObject("secretarias", secretarias.findAllByOrderByNomeAsc());
		mv.addObject("fornecedores", fornecedores.findAllByOrderByNomeAsc());
		mv.addObject("materiais", TipoMaterial.values());
		mv.addObject("itens", tabelaItensContrato.getItens());
		mv.addObject("medidas", UnidadeMedida.values());
		
		mv.addObject(contrato);
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView cadastrar(@Valid Contrato contrato, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(contrato);
		}
		
		for(ItemContrato item : tabelaItensContrato.getItens()) {
			item.setContrato(contrato);
		}
		
		contrato.adicionarItens(tabelaItensContrato.getItens());
		
		try {
			contratoService.cadastrar(contrato);
		} catch (GlobalException e) {
			result.rejectValue("numero", e.getMessage(), e.getMessage());
			return novo(contrato);		
		}
		
		attributes.addFlashAttribute("successMessage", INFORMACOES_SALVAS_SUCESSO);
		
		return new ModelAndView(VIEW_CONTRATO_REDIRECT);
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Contrato contrato) {	
		ModelAndView mv = novo(contrato);
		
		tabelaItensContrato.adicionarItens(contrato.getItens());
		
		mv.addObject("itens", tabelaItensContrato.getItens());
		mv.addObject(contrato);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Contrato contrato) {
		try {
			contratoService.excluir(contrato);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar(ContratoFilter contratoFilter, BindingResult result, @PageableDefault(size = 10) Pageable pageable) {
		ModelAndView mv = new ModelAndView(VIEW_PESQUISAR_CONTRATO);
		mv.addObject("contratos", contratos.findAllByOrderByNumeroAsc());
		mv.addObject("secretarias", secretarias.findAllByOrderByNomeAsc());
		mv.addObject("fornecedores", fornecedores.findAllByOrderByNomeAsc());
		mv.addObject("materiais", TipoMaterial.values());
		mv.addObject("itens", tabelaItensContrato.getItens());
		mv.addObject("medidas", TipoMaterial.values());
		
		Page<Contrato> pagina = contratos.filtrar(contratoFilter, pageable);
		mv.addObject("pagina", pagina);
		
		return mv;
	}
	
	@GetMapping("/contratosPorFornecedor/{codigo}")
	private @ResponseBody ResponseEntity<?> contratosPorFornecedor(@PathVariable("codigo") Long codigo) {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			
		}
		
		return contratos.findByFornecedor(codigo);
	}
}
