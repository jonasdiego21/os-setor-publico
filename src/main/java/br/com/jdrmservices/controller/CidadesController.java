package br.com.jdrmservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdrmservices.model.Cidade;
import br.com.jdrmservices.repository.Cidades;

@RestController
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private Cidades cidades;
	
	@GetMapping
	public List<Cidade> municipiosPorEstado(@RequestParam(name = "codigo", defaultValue = "-1") Long codigoEstado) {
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		
		return cidades.findByEstadoCodigo(codigoEstado);
	}
}
