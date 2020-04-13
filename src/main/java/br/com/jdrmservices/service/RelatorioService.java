package br.com.jdrmservices.service; 

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {

	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarCompraDireta(Long codigo) throws Exception {	
		Map<String, Object> parametros = new HashMap<>();	
		
		File img = new File("src/main/resources/relatorios/lotipo_pmsq.png");
		
		parametros.put("codigo_compra_direta", codigo);
		parametros.put("path_image", img.toString());
		
		return exportandoPdf(parametros, "/relatorios/relatorio_compra_direta.jasper");
	}
	
	public byte[] gerarExecucaoContrato(Long codigo) throws Exception {	
		Map<String, Object> parametros = new HashMap<>();		
		
		parametros.put("execucao_contrato_codigo", codigo);
		parametros.put("path_image", "/relatorios/lotipo_pmsq.png");
		
		return exportandoPdf(parametros, "/relatorios/relatorio_execucao_contrato.jasper");
	}
	
	private byte[] exportandoPdf(Map<String, Object> parametros, String caminhoRelatorio) throws JRException, SQLException {
		parametros.put("format", "pdf");
		
		InputStream inputStream = this.getClass().getResourceAsStream(caminhoRelatorio);		
		Connection connection = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, connection);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			connection.close();
		}	
	}
}
