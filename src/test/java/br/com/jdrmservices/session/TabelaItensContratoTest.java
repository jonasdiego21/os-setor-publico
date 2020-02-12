package br.com.jdrmservices.session;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.jdrmservices.model.ItemContrato;
import br.com.jdrmservices.model.enumeration.UnidadeMedida;

public class TabelaItensContratoTest {

	private TabelaItensContrato tabelaItens;
	
	@Before
	public void setUp() {
		this.tabelaItens = new TabelaItensContrato();
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItens.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		ItemContrato item = new ItemContrato();
		
		item.setCodigo(1L);
		item.setMarca("Sony");
		item.setEspecificacao("Camera fotográfica t4i");
		item.setQuantidade(1);
		item.setUnidadeMedida(UnidadeMedida.UN);
		item.setValorUnitario(new BigDecimal("1156.59"));
		
		this.tabelaItens.adicionarItem(item);
		
		assertEquals(new BigDecimal("1156.59"), this.tabelaItens.getValorTotal());		
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		ItemContrato item1 = new ItemContrato();
		ItemContrato item2 = new ItemContrato();
		ItemContrato item3 = new ItemContrato();
		
		item1.setCodigo(1L);
		item1.setMarca("Epson");
		item1.setEspecificacao("Camera fotográfica D4i");
		item1.setQuantidade(1);
		item1.setUnidadeMedida(UnidadeMedida.UN);
		item1.setValorUnitario(new BigDecimal("2000.00"));
		
		item2.setCodigo(2L);
		item2.setMarca("Sony");
		item2.setEspecificacao("Camera fotográfica t4i");
		item2.setQuantidade(1);
		item2.setUnidadeMedida(UnidadeMedida.UN);
		item2.setValorUnitario(new BigDecimal("1500.00"));
		
		item3.setCodigo(3L);
		item3.setMarca("Canon");
		item3.setEspecificacao("Camera fotográfica F4i");
		item3.setQuantidade(1);
		item3.setUnidadeMedida(UnidadeMedida.UN);
		item3.setValorUnitario(new BigDecimal("1500.00"));
		
		this.tabelaItens.adicionarItem(item1);
		this.tabelaItens.adicionarItem(item2);
		this.tabelaItens.adicionarItem(item3);
		
		assertEquals(new BigDecimal("5000.00"), this.tabelaItens.getValorTotal());		
	}
}
