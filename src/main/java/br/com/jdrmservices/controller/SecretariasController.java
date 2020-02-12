package br.com.jdrmservices.controller;

import static br.com.jdrmservices.util.Constants.INFORMACOES_SALVAS_SUCESSO;
import static br.com.jdrmservices.util.Constants.VIEW_PESQUISAR_SECRETARIA;
import static br.com.jdrmservices.util.Constants.VIEW_SECRETARIA_NOVO;
import static br.com.jdrmservices.util.Constants.VIEW_SECRETARIA_REDIRECT;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import br.com.jdrmservices.model.Empresa;
import br.com.jdrmservices.model.Secretaria;
import br.com.jdrmservices.model.Usuario;
import br.com.jdrmservices.repository.Empresas;
import br.com.jdrmservices.repository.Secretarias;
import br.com.jdrmservices.repository.filter.SecretariaFilter;
import br.com.jdrmservices.service.SecretariaService;

@Controller
@RequestMapping("/secretarias")
public class SecretariasController {

	@Autowired
	private SecretariaService secretariaService;
	
	@Autowired
	private Secretarias secretarias;
	
	@Autowired
	private Empresas empresas;
	
	@GetMapping("/novo")
	public ModelAndView novo(Secretaria secretaria) {
		ModelAndView mv = new ModelAndView(VIEW_SECRETARIA_NOVO);
		mv.addObject("secretarias", secretarias.findAllByOrderByNomeAsc());
		mv.addObject(secretaria);
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView cadastrar(@Valid Secretaria secretaria, BindingResult result, Model model, RedirectAttributes attributes, @AuthenticationPrincipal Usuario usuario) {
		
		Empresa empresa = empresas.findByUsuario(usuario);
		
		secretaria.setEmpresa(empresa);
		
		if(result.hasErrors()) {
			return novo(secretaria);
		}
		
		try {
			secretariaService.cadastrar(secretaria);
		} catch (GlobalException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(secretaria);		
		}
		
		attributes.addFlashAttribute("successMessage", INFORMACOES_SALVAS_SUCESSO);
		
		return new ModelAndView(VIEW_SECRETARIA_REDIRECT);
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Secretaria secretaria) {	
		ModelAndView mv = novo(secretaria);
		mv.addObject(secretaria);
		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Secretaria secretaria) {
		try {
			secretariaService.excluir(secretaria);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();	
	}
	
	@GetMapping
	public ModelAndView pesquisar(SecretariaFilter secretariaFilter, BindingResult result, @PageableDefault(size = 25) Pageable pageable) {
		ModelAndView mv = new ModelAndView(VIEW_PESQUISAR_SECRETARIA);
		mv.addObject("secretarias", secretarias.findAllByOrderByNomeAsc());
		
		Page<Secretaria> pagina = secretarias.filtrar(secretariaFilter, pageable);
		mv.addObject("pagina", pagina);
		
		return mv;
	}
}