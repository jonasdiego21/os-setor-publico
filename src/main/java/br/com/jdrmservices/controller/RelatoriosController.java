package br.com.jdrmservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jdrmservices.service.RelatorioService;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {

	@Autowired
	private RelatorioService relatorioService;
	
	@GetMapping("/servico/{codigo}")
	public ResponseEntity<byte[]> gerarOrdemServicoEetiqueta(@PathVariable Long codigo) throws Exception {
		byte[] relatorio = relatorioService.gerarOrdemServicoEetiqueta(codigo);
		
		return relatorioEmPdf(relatorio);
	}
	
	private ResponseEntity<byte[]> relatorioEmPdf(byte[] relatorio) {
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
	}
}
