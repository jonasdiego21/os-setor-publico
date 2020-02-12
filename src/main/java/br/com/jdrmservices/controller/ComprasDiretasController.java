package br.com.jdrmservices.controller;

import static br.com.jdrmservices.util.Constants.INFORMACOES_SALVAS_SUCESSO;
import static br.com.jdrmservices.util.Constants.VIEW_PESQUISAR_COMPRA_DIRETA;
import static br.com.jdrmservices.util.Constants.VIEW_COMPRA_DIRETA_NOVO;
import static br.com.jdrmservices.util.Constants.VIEW_COMPRA_DIRETA_REDIRECT;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jdrmservices.exception.GlobalException;
import br.com.jdrmservices.model.CompraDireta;
import br.com.jdrmservices.model.enumeration.UnidadeMedida;
import br.com.jdrmservices.repository.ComprasDiretas;
import br.com.jdrmservices.repository.Fornecedores;
import br.com.jdrmservices.repository.Secretarias;
import br.com.jdrmservices.repository.filter.CompraDiretaFilter;
import br.com.jdrmservices.service.CompraDiretaService;

@Controller
@RequestMapping("/comprasdiretas")
public class ComprasDiretasController {

	@Autowired
	private CompraDiretaService compraDiretaService;
	
	@Autowired
	private ComprasDiretas comprasDiretas;
	
	@Autowired
	private Secretarias secretarias;
	
	@Autowired
	private Fornecedores fornecedores;
	
	@GetMapping("/novo")
	public ModelAndView novo(CompraDireta compraDireta) {
		ModelAndView mv = new ModelAndView(VIEW_COMPRA_DIRETA_NOVO);
		mv.addObject("comprasdiretas", comprasDiretas.findAll());
		mv.addObject("secretarias", secretarias.findAllByOrderByNomeAsc());
		mv.addObject("fornecedores", fornecedores.findAllByOrderByNomeAsc());
		mv.addObject("medidas", UnidadeMedida.values());
		mv.addObject(compraDireta);
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView cadastrar(@Valid CompraDireta compraDireta, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(compraDireta);
		}
		
		try {
			compraDiretaService.cadastrar(compraDireta);
		} catch (GlobalException e) {
			result.rejectValue("numero", e.getMessage(), e.getMessage());
			return novo(compraDireta);		
		}
		
		attributes.addFlashAttribute("successMessage", INFORMACOES_SALVAS_SUCESSO);
		
		return new ModelAndView(VIEW_COMPRA_DIRETA_REDIRECT);
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") CompraDireta compraDireta) {	
		ModelAndView mv = novo(compraDireta);
		mv.addObject(compraDireta);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") CompraDireta compraDireta) {
		try {
			compraDiretaService.excluir(compraDireta);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar(CompraDiretaFilter compraDiretaFilter, BindingResult result, @PageableDefault(size = 10) Pageable pageable) {
		ModelAndView mv = new ModelAndView(VIEW_PESQUISAR_COMPRA_DIRETA);
		mv.addObject("comprasdiretas", comprasDiretas.findAll());
		mv.addObject("secretarias", secretarias.findAllByOrderByNomeAsc());
		mv.addObject("fornecedores", fornecedores.findAllByOrderByNomeAsc());
		mv.addObject("medidas", UnidadeMedida.values());
		
		Page<CompraDireta> pagina = comprasDiretas.filtrar(compraDiretaFilter, pageable);
		mv.addObject("pagina", pagina);
		
		return mv;
	}
}