package br.com.jdrmservices.controller;

import static br.com.jdrmservices.util.Constants.VIEW_DASHBOARD;
import static br.com.jdrmservices.util.Constants.VIEW_INDEX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jdrmservices.repository.ComprasDiretas;
import br.com.jdrmservices.repository.Contratos;
import br.com.jdrmservices.repository.Empresas;
import br.com.jdrmservices.repository.ExecucaoContratos;
import br.com.jdrmservices.repository.Fornecedores;
import br.com.jdrmservices.repository.Secretarias;
import br.com.jdrmservices.repository.Usuarios;
import br.com.jdrmservices.security.UsuarioSistema;

@Controller
@RequestMapping("/")
public class DashboardController {

	@Autowired
	private Empresas empresas;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Secretarias secretarias;
	
	@Autowired
	private Contratos contratos;
	
	@Autowired
	private Fornecedores fornecedores;
	
	@Autowired
	private ComprasDiretas comprasDiretas;
	
	@Autowired
	private ExecucaoContratos execurcaoContratos;
	
	@GetMapping
	public ModelAndView dashboard(@AuthenticationPrincipal UsuarioSistema usuarioSistema) {	
		ModelAndView mv = new ModelAndView(VIEW_DASHBOARD);
		mv.addObject("empresas", empresas.count());
		
		mv.addObject("usuarios", usuarios.count());
		mv.addObject("secretarias", secretarias.count());
		mv.addObject("contratos", contratos.count());
		
		mv.addObject("fornecedores", fornecedores.count());
		mv.addObject("comprasdiretas", comprasDiretas.count());
		mv.addObject("execucaocontratos", execurcaoContratos.count());
		
		return mv;
	}
}