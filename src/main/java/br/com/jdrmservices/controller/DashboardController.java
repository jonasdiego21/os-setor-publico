package br.com.jdrmservices.controller;

import static br.com.jdrmservices.util.Constants.VIEW_DASHBOARD;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.jdrmservices.security.UsuarioSistema;

@Controller
@RequestMapping("/")
public class DashboardController {

	@GetMapping
	public ModelAndView index(@AuthenticationPrincipal UsuarioSistema usuarioSistema) {	
		ModelAndView mv = new ModelAndView(VIEW_DASHBOARD);
		
		return mv;
	}
}