package br.com.jdrmservices.controller;

import java.io.File;

import javax.activation.FileTypeMap;

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
	
	@GetMapping("{logo}")
	public MediaType logo(@PathVariable("logo") String image) {
		File img = new File("src/main/resources/relatorios/" + image);
		
		return MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img));
	}
	
	@GetMapping("/compradireta/{codigo}")
	public ResponseEntity<byte[]> gerarCompraDireta(@PathVariable Long codigo) throws Exception {
		byte[] relatorio = relatorioService.gerarCompraDireta(codigo);
		
		return relatorioEmPdf(relatorio);
	}

	@GetMapping("/execucaocontrato/{codigo}")
	public ResponseEntity<byte[]> gerarExecucaoContrato(@PathVariable Long codigo) throws Exception {
		byte[] relatorio = relatorioService.gerarExecucaoContrato(codigo);
		
		return relatorioEmPdf(relatorio);
	}
	
	private ResponseEntity<byte[]> relatorioEmPdf(byte[] relatorio) {
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);
	}
}
