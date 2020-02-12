package br.com.jdrmservices.controller;

import static br.com.jdrmservices.util.Constants.INFORMACOES_SALVAS_SUCESSO;
import static br.com.jdrmservices.util.Constants.VIEW_PESQUISAR_EXECUCAO_CONTRATO;
import static br.com.jdrmservices.util.Constants.VIEW_EXECUCAO_CONTRATO_NOVO;
import static br.com.jdrmservices.util.Constants.VIEW_EXECUCAO_CONTRATO_REDIRECT;

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
import br.com.jdrmservices.model.ExecucaoContrato;
import br.com.jdrmservices.repository.ExecucaoContratos;
import br.com.jdrmservices.repository.filter.ExecucaoContratoFilter;
import br.com.jdrmservices.service.ExecucaoContratoService;

@Controller
@RequestMapping("/execucaocontratos")
public class ExecucaoContratosController {

	@Autowired
	private ExecucaoContratoService execucaoContratoService;
	
	@Autowired
	private ExecucaoContratos execucaoContratos;
	
	@GetMapping("/novo")
	public ModelAndView novo(ExecucaoContrato execucaoContrato) {
		ModelAndView mv = new ModelAndView(VIEW_EXECUCAO_CONTRATO_NOVO);
		mv.addObject("execucaocontratos", execucaoContratos.findAllByOrderByFornecedorAsc());
		mv.addObject(execucaoContrato);
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView cadastrar(@Valid ExecucaoContrato execucaoContrato, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return novo(execucaoContrato);
		}
		
		try {
			execucaoContratoService.cadastrar(execucaoContrato);
		} catch (GlobalException e) {
			result.rejectValue("fornecedor", e.getMessage(), e.getMessage());
			return novo(execucaoContrato);		
		}
		
		attributes.addFlashAttribute("successMessage", INFORMACOES_SALVAS_SUCESSO);
		
		return new ModelAndView(VIEW_EXECUCAO_CONTRATO_REDIRECT);
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") ExecucaoContrato execucaoContrato) {	
		ModelAndView mv = novo(execucaoContrato);
		mv.addObject(execucaoContrato);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") ExecucaoContrato execucaoContrato) {
		try {
			execucaoContratoService.excluir(execucaoContrato);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar(ExecucaoContratoFilter execucaoContratoFilter, BindingResult result, @PageableDefault(size = 10) Pageable pageable) {
		ModelAndView mv = new ModelAndView(VIEW_PESQUISAR_EXECUCAO_CONTRATO);
		mv.addObject("execucaocontratos", execucaoContratos.findAllByOrderByFornecedorAsc());
		
		Page<ExecucaoContrato> pagina = execucaoContratos.filtrar(execucaoContratoFilter, pageable);
		mv.addObject("pagina", pagina);
		
		return mv;
	}
}